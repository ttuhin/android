package com.tuhin.skfreefall;

import java.lang.System;

/**
 * Implemented this in order for FallDetector to be able to callback in fall event
 *
 * This should be provided to
 * {@link FallDetector #FallDetector(android.content.Context, FallDetectorCallback)}.
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/tuhin/skfreefall/FallDetectorCallback;", "", "onFallDetected", "", "freeFallData", "Lcom/tuhin/skfreefall/data/model/FreeFallData;", "skfreefall_release"})
public abstract interface FallDetectorCallback {
    
    /**
     * Called when a free fall event occurred.
     *
     * @param freeFallData The information of free fall event.
     */
    public abstract void onFallDetected(@org.jetbrains.annotations.NotNull()
    com.tuhin.skfreefall.data.model.FreeFallData freeFallData);
}