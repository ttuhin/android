package com.tuhin.skfreefall.data.database;

import java.lang.System;

@androidx.room.Database(entities = {com.tuhin.skfreefall.data.model.FreeFallData.class}, version = 1, exportSchema = false)
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b!\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lcom/tuhin/skfreefall/data/database/FallDetectorDatabase;", "Landroidx/room/RoomDatabase;", "()V", "fallEventDao", "Lcom/tuhin/skfreefall/data/database/FallEventDao;", "Companion", "skfreefall_release"})
public abstract class FallDetectorDatabase extends androidx.room.RoomDatabase {
    private static com.tuhin.skfreefall.data.database.FallDetectorDatabase instance;
    public static final com.tuhin.skfreefall.data.database.FallDetectorDatabase.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.tuhin.skfreefall.data.database.FallEventDao fallEventDao();
    
    public FallDetectorDatabase() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/tuhin/skfreefall/data/database/FallDetectorDatabase$Companion;", "", "()V", "instance", "Lcom/tuhin/skfreefall/data/database/FallDetectorDatabase;", "destroyDataBase", "", "getAppDataBase", "context", "Landroid/content/Context;", "skfreefall_release"})
    public static final class Companion {
        
        @org.jetbrains.annotations.Nullable()
        public final com.tuhin.skfreefall.data.database.FallDetectorDatabase getAppDataBase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        public final void destroyDataBase() {
        }
        
        private Companion() {
            super();
        }
    }
}