package com.tuhin.skfreefall.data.model;

import java.lang.System;

/**
 * This is a model class to hold free fall information
 *
 * @param timeStamp The time in millisecond at which the free fall happened
 * @param duration The duration of the free fall in milliseconds
 */
@androidx.room.Entity(tableName = "FreeFallData")
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t\u00a8\u0006\u0015"}, d2 = {"Lcom/tuhin/skfreefall/data/model/FreeFallData;", "", "timeStamp", "", "duration", "(Ljava/lang/String;Ljava/lang/String;)V", "getDuration", "()Ljava/lang/String;", "setDuration", "(Ljava/lang/String;)V", "getTimeStamp", "setTimeStamp", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "skfreefall_release"})
public final class FreeFallData {
    @org.jetbrains.annotations.NotNull()
    @androidx.room.PrimaryKey(autoGenerate = false)
    private java.lang.String timeStamp;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String duration;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTimeStamp() {
        return null;
    }
    
    public final void setTimeStamp(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDuration() {
        return null;
    }
    
    public final void setDuration(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public FreeFallData(@org.jetbrains.annotations.NotNull()
    java.lang.String timeStamp, @org.jetbrains.annotations.NotNull()
    java.lang.String duration) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    /**
     * This is a model class to hold free fall information
     *
     * @param timeStamp The time in millisecond at which the free fall happened
     * @param duration The duration of the free fall in milliseconds
     */
    @org.jetbrains.annotations.NotNull()
    public final com.tuhin.skfreefall.data.model.FreeFallData copy(@org.jetbrains.annotations.NotNull()
    java.lang.String timeStamp, @org.jetbrains.annotations.NotNull()
    java.lang.String duration) {
        return null;
    }
    
    /**
     * This is a model class to hold free fall information
     *
     * @param timeStamp The time in millisecond at which the free fall happened
     * @param duration The duration of the free fall in milliseconds
     */
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    /**
     * This is a model class to hold free fall information
     *
     * @param timeStamp The time in millisecond at which the free fall happened
     * @param duration The duration of the free fall in milliseconds
     */
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    /**
     * This is a model class to hold free fall information
     *
     * @param timeStamp The time in millisecond at which the free fall happened
     * @param duration The duration of the free fall in milliseconds
     */
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
        return false;
    }
}