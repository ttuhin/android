package com.tuhin.skfreefall

import com.tuhin.skfreefall.data.model.FreeFallData
import com.tuhin.skfreefall.data.repository.FreeFallRepository

import android.content.*
import android.os.IBinder

import io.reactivex.Completable
import io.reactivex.Observable

/**
 * FallDetector class which provides
 * several APIs to detect free fall event of android devices.
 *
 * @sample com.tuhin.skfreefall.sample.FallDetectorClient
 */
class FallDetector constructor(context: Context, fallDetectorCallback: FallDetectorCallback) {

    private var bound = false
    private var freeFallService: FallDetectorService? = null
    private val intent = Intent(context, FallDetectorService::class.java)
    private val context = context
    private val fallDetectListener: FallDetectorCallback = fallDetectorCallback
    private val freeFallRepository: FreeFallRepository = FreeFallRepository(context.applicationContext)

    /**
     * Start detection of free fall event
     *
     * @see stopDetection
     */
    fun startDetection() {
        if (!isRunning()) {
            context.bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    /**
     * Stop detection of free fall event.
     *
     * @see startDetection
     */
    fun stopDetection() {
        if (isRunning()) {
            bound = false
            freeFallService?.stop()
            context.unbindService(connection)
        }
    }

    /**
     * Return the current detection state.
     *
     * @return <code>true</code> if the detection had been started;
     *         <code>false</code> otherwise.
     */
    fun isRunning() = bound

    /**
     * Adjust threshold value for free fall state of device.
     *
     * Due to sensor noise accelerometer reading can be varied from device to device.
     *
     * Therefore, threshold value can be customized through this API.
     *
     * @param value minimum threshold. Default 1.0
     * @see getMinThreshold
     */
    fun setMinThreshold(value: Double) {
        freeFallService?.minThreshold = value
    }

    /**
     * Adjust threshold value for rest state of device.
     *
     * Due to sensor noise accelerometer reading can be varied from device to device.
     *
     * Therefore, threshold value can be customized through this API.
     *
     * @param value maximum threshold .Default 9.0;
     * @see getMaxThreshold
     */
    fun setMaxThreshold(value: Double) {
        freeFallService?.maxThreshold = value
    }

    /**
     * Get minimum threshold value
     *
     * @return <code>Double</code> minimum threshold value.
     * @see setMinThreshold
     */
    fun getMinThreshold() = freeFallService?.minThreshold!!

    /**
     * Get maximum threshold value
     *
     * @return <code>Double</code> maximum threshold value.
     * @see setMaxThreshold
     */
    fun getMaxThreshold() = freeFallService?.maxThreshold!!

    /**
     * Provide all free fall events occurred in the device
     * which had been saved in the local database and can be listened by implementing observer.
     *
     * @return Observable<List<FreeFallData>> an observable of list of free fall events that can be notified to caller
     * when there is a change in event list.
     */
    fun getAllEvents(): Observable<List<FreeFallData>> = freeFallRepository.getAllEvent()

    /**
     * Removed all free fall events occurred in the device
     * from the local database permanently.
     *
     * @return Completable an observable through which success and error can be notified to caller.
     */
    fun deleteAllEvents(): Completable = freeFallRepository.deleteAllEvent()

    private fun initSetup() {
        freeFallService?.run {
            start()
            setRepository(freeFallRepository)
            setListener(fallDetectListener)
        }
    }

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as FallDetectorService.LocalBinder
            freeFallService = binder.getService()
            bound = true
            initSetup()
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            bound = false
        }
    }

}