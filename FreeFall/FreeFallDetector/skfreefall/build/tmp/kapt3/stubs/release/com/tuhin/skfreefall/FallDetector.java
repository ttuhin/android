package com.tuhin.skfreefall;

import java.lang.System;

/**
 * FallDetector class which provides
 * several APIs to detect free fall event of android devices.
 *
 * @sample com.tuhin.skfreefall.sample.FallDetectorClient
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0012\u001a\u00020\u0013J\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u0015J\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u0019J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u0006\u0010\u001d\u001a\u00020\bJ\u000e\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u0019J\u000e\u0010 \u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u0019J\u0006\u0010!\u001a\u00020\u001cJ\u0006\u0010\"\u001a\u00020\u001cR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/tuhin/skfreefall/FallDetector;", "", "context", "Landroid/content/Context;", "fallDetectorCallback", "Lcom/tuhin/skfreefall/FallDetectorCallback;", "(Landroid/content/Context;Lcom/tuhin/skfreefall/FallDetectorCallback;)V", "bound", "", "connection", "Landroid/content/ServiceConnection;", "fallDetectListener", "freeFallRepository", "Lcom/tuhin/skfreefall/data/repository/FreeFallRepository;", "freeFallService", "Lcom/tuhin/skfreefall/FallDetectorService;", "intent", "Landroid/content/Intent;", "deleteAllEvents", "Lio/reactivex/Completable;", "getAllEvents", "Lio/reactivex/Observable;", "", "Lcom/tuhin/skfreefall/data/model/FreeFallData;", "getMaxThreshold", "", "getMinThreshold", "initSetup", "", "isRunning", "setMaxThreshold", "value", "setMinThreshold", "startDetection", "stopDetection", "skfreefall_release"})
public final class FallDetector {
    private boolean bound;
    private com.tuhin.skfreefall.FallDetectorService freeFallService;
    private final android.content.Intent intent = null;
    private final android.content.Context context = null;
    private final com.tuhin.skfreefall.FallDetectorCallback fallDetectListener = null;
    private final com.tuhin.skfreefall.data.repository.FreeFallRepository freeFallRepository = null;
    private final android.content.ServiceConnection connection = null;
    
    /**
     * Start detection of free fall event
     *
     * @see stopDetection
     */
    public final void startDetection() {
    }
    
    /**
     * Stop detection of free fall event.
     *
     * @see startDetection
     */
    public final void stopDetection() {
    }
    
    /**
     * Return the current detection state.
     *
     * @return <code>true</code> if the detection had been started;
     *        <code>false</code> otherwise.
     */
    public final boolean isRunning() {
        return false;
    }
    
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
    public final void setMinThreshold(double value) {
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
    public final void setMaxThreshold(double value) {
    }
    
    /**
     * Get minimum threshold value
     *
     * @return <code>Double</code> minimum threshold value.
     * @see setMinThreshold
     */
    public final double getMinThreshold() {
        return 0.0;
    }
    
    /**
     * Get maximum threshold value
     *
     * @return <code>Double</code> maximum threshold value.
     * @see setMaxThreshold
     */
    public final double getMaxThreshold() {
        return 0.0;
    }
    
    /**
     * Provide all free fall events occurred in the device
     * which had been saved in the local database and can be listened by implementing observer.
     *
     * @return Observable<List<FreeFallData>> an observable of list of free fall events that can be notified to caller
     * when there is a change in event list.
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<java.util.List<com.tuhin.skfreefall.data.model.FreeFallData>> getAllEvents() {
        return null;
    }
    
    /**
     * Removed all free fall events occurred in the device
     * from the local database permanently.
     *
     * @return Completable an observable through which success and error can be notified to caller.
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Completable deleteAllEvents() {
        return null;
    }
    
    private final void initSetup() {
    }
    
    public FallDetector(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.tuhin.skfreefall.FallDetectorCallback fallDetectorCallback) {
        super();
    }
}