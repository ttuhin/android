package com.tuhin.skfreefall

import com.tuhin.skfreefall.data.model.FreeFallData

/**
 * Implemented this in order for FallDetector to be able to callback in fall event
 *
 * This should be provided to
 * {@link FallDetector #FallDetector(android.content.Context, FallDetectorCallback)}.
 */
interface FallDetectorCallback {

    /**
     * Called when a free fall event occurred.
     *
     * @param freeFallData The information of free fall event.
     */
    fun onFallDetected(freeFallData: FreeFallData)
}