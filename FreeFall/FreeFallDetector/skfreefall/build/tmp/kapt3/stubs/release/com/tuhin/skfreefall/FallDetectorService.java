package com.tuhin.skfreefall;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 92\u00020\u00012\u00020\u0002:\u00029:B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020#H\u0002J\u001a\u0010%\u001a\u00020#2\b\u0010&\u001a\u0004\u0018\u00010\u00052\u0006\u0010\'\u001a\u00020(H\u0016J\u0014\u0010)\u001a\u0004\u0018\u00010*2\b\u0010+\u001a\u0004\u0018\u00010,H\u0016J\b\u0010-\u001a\u00020#H\u0016J\u0012\u0010.\u001a\u00020#2\b\u0010/\u001a\u0004\u0018\u000100H\u0016J\u0010\u00101\u001a\u00020#2\u0006\u00102\u001a\u000203H\u0002J\b\u00104\u001a\u00020#H\u0002J\u0010\u00105\u001a\u00020#2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u000e\u00106\u001a\u00020#2\u0006\u0010\f\u001a\u00020\rJ\u0006\u00107\u001a\u00020#J\u0006\u00108\u001a\u00020#R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00060\u0007R\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006;"}, d2 = {"Lcom/tuhin/skfreefall/FallDetectorService;", "Landroid/app/Service;", "Landroid/hardware/SensorEventListener;", "()V", "accelerometer", "Landroid/hardware/Sensor;", "binder", "Lcom/tuhin/skfreefall/FallDetectorService$LocalBinder;", "endTime", "", "fallDetectListener", "Lcom/tuhin/skfreefall/FallDetectorCallback;", "freeFallRepository", "Lcom/tuhin/skfreefall/data/repository/FreeFallRepository;", "handler", "Landroid/os/Handler;", "handlerThread", "Landroid/os/HandlerThread;", "maxThreshold", "", "getMaxThreshold", "()D", "setMaxThreshold", "(D)V", "minThreshold", "getMinThreshold", "setMinThreshold", "notification", "Landroid/app/Notification;", "sensorManager", "Landroid/hardware/SensorManager;", "startTime", "started", "", "createNotification", "", "createNotificationChannel", "onAccuracyChanged", "sensor", "accuracy", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onSensorChanged", "event", "Landroid/hardware/SensorEvent;", "saveToDatabaseAndSendToClient", "freeFallData", "Lcom/tuhin/skfreefall/data/model/FreeFallData;", "sendNotification", "setListener", "setRepository", "start", "stop", "Companion", "LocalBinder", "skfreefall_release"})
public final class FallDetectorService extends android.app.Service implements android.hardware.SensorEventListener {
    private long startTime;
    private long endTime;
    private double minThreshold;
    private double maxThreshold;
    private android.hardware.SensorManager sensorManager;
    private android.hardware.Sensor accelerometer;
    private boolean started;
    private com.tuhin.skfreefall.data.repository.FreeFallRepository freeFallRepository;
    private final com.tuhin.skfreefall.FallDetectorService.LocalBinder binder = null;
    private android.os.HandlerThread handlerThread;
    private com.tuhin.skfreefall.FallDetectorCallback fallDetectListener;
    private android.app.Notification notification;
    private android.os.Handler handler;
    private static final java.lang.String NOTIFICATION_TITLE = "FreeFall Detector";
    private static final java.lang.String NOTIFICATION_MESSAGE = "Fall Detected";
    private static final java.lang.String CHANNEL_ID = "FallDetectorChannel";
    private static final java.lang.String CHANNEL_NAME = "FallDetectorChannel";
    private static final int NOTIFICATION_ID = 1;
    private static final long DELAY_BETWEEN_EVENTS = 1000L;
    private static final int X_AXIS_INDEX = 0;
    private static final int Y_AXIS_INDEX = 1;
    private static final int Z_AXIS_INDEX = 2;
    private static final int MILLISECOND_DIVISOR = 1000000;
    public static final com.tuhin.skfreefall.FallDetectorService.Companion Companion = null;
    
    public final double getMinThreshold() {
        return 0.0;
    }
    
    public final void setMinThreshold(double p0) {
    }
    
    public final double getMaxThreshold() {
        return 0.0;
    }
    
    public final void setMaxThreshold(double p0) {
    }
    
    private final void createNotificationChannel() {
    }
    
    private final void createNotification() {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return null;
    }
    
    public final void setRepository(@org.jetbrains.annotations.NotNull()
    com.tuhin.skfreefall.data.repository.FreeFallRepository freeFallRepository) {
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    public final void setListener(@org.jetbrains.annotations.Nullable()
    com.tuhin.skfreefall.FallDetectorCallback fallDetectListener) {
    }
    
    public final void start() {
    }
    
    public final void stop() {
    }
    
    @java.lang.Override()
    public void onAccuracyChanged(@org.jetbrains.annotations.Nullable()
    android.hardware.Sensor sensor, int accuracy) {
    }
    
    @java.lang.Override()
    public void onSensorChanged(@org.jetbrains.annotations.Nullable()
    android.hardware.SensorEvent event) {
    }
    
    private final void saveToDatabaseAndSendToClient(com.tuhin.skfreefall.data.model.FreeFallData freeFallData) {
    }
    
    private final void sendNotification() {
    }
    
    public FallDetectorService() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/tuhin/skfreefall/FallDetectorService$LocalBinder;", "Landroid/os/Binder;", "(Lcom/tuhin/skfreefall/FallDetectorService;)V", "getService", "Lcom/tuhin/skfreefall/FallDetectorService;", "skfreefall_release"})
    public final class LocalBinder extends android.os.Binder {
        
        @org.jetbrains.annotations.NotNull()
        public final com.tuhin.skfreefall.FallDetectorService getService() {
            return null;
        }
        
        public LocalBinder() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/tuhin/skfreefall/FallDetectorService$Companion;", "", "()V", "CHANNEL_ID", "", "CHANNEL_NAME", "DELAY_BETWEEN_EVENTS", "", "MILLISECOND_DIVISOR", "", "NOTIFICATION_ID", "NOTIFICATION_MESSAGE", "NOTIFICATION_TITLE", "X_AXIS_INDEX", "Y_AXIS_INDEX", "Z_AXIS_INDEX", "skfreefall_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}