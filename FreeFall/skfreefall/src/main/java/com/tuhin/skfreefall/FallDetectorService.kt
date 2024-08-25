package com.tuhin.skfreefall

import com.tuhin.skfreefall.util.TimeUtil
import com.tuhin.skfreefall.data.model.FreeFallData
import com.tuhin.skfreefall.data.repository.FreeFallRepository

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager

import android.app.Service
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.*

import androidx.core.app.NotificationCompat

import kotlin.math.sqrt

internal class FallDetectorService : Service(), SensorEventListener {

    private var startTime = 0L
    private var endTime = 0L
    var minThreshold = 1.0
    var maxThreshold = 9.0

    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private var started = false
    private var freeFallRepository: FreeFallRepository? = null
    private val binder = LocalBinder()
    private var handlerThread: HandlerThread? = null
    private var fallDetectListener: FallDetectorCallback? = null
    private var notification: Notification? = null
    private var handler:Handler? = null

    companion object {
        private const val NOTIFICATION_TITLE = "FreeFall Detector"
        private const val NOTIFICATION_MESSAGE = "Fall Detected"
        private const val CHANNEL_ID = "FallDetectorChannel"
        private const val CHANNEL_NAME = "FallDetectorChannel"
        private const val NOTIFICATION_ID = 1
        private const val DELAY_BETWEEN_EVENTS = 1000L // in ms
        private const val X_AXIS_INDEX = 0
        private const val Y_AXIS_INDEX = 1
        private const val Z_AXIS_INDEX = 2
        private const val MILLISECOND_DIVISOR = 1000000
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    private fun createNotification() {
        notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(NOTIFICATION_TITLE)
            .setContentText(NOTIFICATION_MESSAGE)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
    }

    override fun onBind(intent: Intent?): IBinder? {
        createNotificationChannel()
        createNotification()
        return binder
    }

    fun setRepository(freeFallRepository: FreeFallRepository) {
        this.freeFallRepository = freeFallRepository
    }

    override fun onCreate() {
        super.onCreate()
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        handlerThread = HandlerThread(FallDetectorService::class.java.simpleName)
    }

    fun setListener(fallDetectListener: FallDetectorCallback?) {
        this.fallDetectListener = fallDetectListener
    }

    fun start() {
        handlerThread?.start()
        handler = Handler(handlerThread?.looper)
        sensorManager.registerListener(
            this,
            accelerometer,
            SensorManager.SENSOR_DELAY_NORMAL,
            handler
        )
    }

    fun stop() {
        sensorManager.unregisterListener(this)
        handlerThread?.run {
            if (isAlive) {
                quitSafely()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null && event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            val values = event.values
            val x = values?.get(X_AXIS_INDEX)
            val y = values?.get(Y_AXIS_INDEX)
            val z = values?.get(Z_AXIS_INDEX)

            val g = sqrt(x?.times(x)!! + y?.times(y)!! + z?.times(z)!!).toDouble()

            var current =  System.currentTimeMillis() + (event.timestamp - SystemClock.elapsedRealtimeNanos()) / MILLISECOND_DIVISOR

            if (current - endTime >= DELAY_BETWEEN_EVENTS) {

                if (g < minThreshold && !started) {
                    startTime =
                        System.currentTimeMillis() + (event.timestamp - SystemClock.elapsedRealtimeNanos()) / MILLISECOND_DIVISOR
                    started = true
                }

                if (started && g > maxThreshold) {
                    endTime =
                        System.currentTimeMillis() + (event.timestamp - SystemClock.elapsedRealtimeNanos()) / MILLISECOND_DIVISOR
                    saveToDatabaseAndSendToClient(FreeFallData(TimeUtil.getTimeStampInDateTimeFormat(startTime),
                        (endTime - startTime).toString()))
                    sendNotification()
                    started = false
                }
            }
        }
    }

    private fun saveToDatabaseAndSendToClient(freeFallData: FreeFallData) {
        fallDetectListener?.onFallDetected(freeFallData)
        freeFallRepository?.saveEvent(freeFallData)?.subscribe()
    }

    private fun sendNotification() {
        startForeground(NOTIFICATION_ID, notification)
    }

    inner class LocalBinder : Binder() {
        fun getService(): FallDetectorService = this@FallDetectorService
    }
}