package com.tuhin.skfreefall.sample

import com.tuhin.skfreefall.FallDetector
import com.tuhin.skfreefall.FallDetectorCallback
import com.tuhin.skfreefall.data.model.FreeFallData

import androidx.appcompat.app.AppCompatActivity

import io.reactivex.android.schedulers.AndroidSchedulers

class FallDetectorClient : AppCompatActivity() {
    fun fallDetectionClientExample() {
        //create fall detection object
        val fallDetector = FallDetector(this, object : FallDetectorCallback {
            override fun onFallDetected(freeFallData: FreeFallData) {
                val latestFallData: String = String.format(
                    freeFallData.timeStamp,
                    freeFallData.duration
                )
                runOnUiThread {
                    //update UI or print log
                }
            }
        })

        if (!fallDetector.isRunning()) {
            fallDetector.apply {
                //set min threshold
                setMinThreshold(1.0)
                //set max threshold
                setMaxThreshold(9.0)
                //start detection
                startDetection()
                //remove all events
                fallDetector.getAllEvents().observeOn(AndroidSchedulers.mainThread()).subscribe({
                    //update UI or print log
                }) {
                    //update UI or print log
                }

                /*
                To remove all events from database
                 */
                fallDetector.deleteAllEvents().observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        //update UI or print log
                    }
            }
        }
    }
}