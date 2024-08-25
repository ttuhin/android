package com.tuhin.skfreefall.data.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\fJ\u0012\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eJ\u000e\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0010R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/tuhin/skfreefall/data/repository/FreeFallRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "database", "Lcom/tuhin/skfreefall/data/database/FallDetectorDatabase;", "fallEventDao", "Lcom/tuhin/skfreefall/data/database/FallEventDao;", "closeDataSource", "", "deleteAllEvent", "Lio/reactivex/Completable;", "getAllEvent", "Lio/reactivex/Observable;", "", "Lcom/tuhin/skfreefall/data/model/FreeFallData;", "saveEvent", "user", "skfreefall_debug"})
public final class FreeFallRepository {
    private final com.tuhin.skfreefall.data.database.FallDetectorDatabase database = null;
    private final com.tuhin.skfreefall.data.database.FallEventDao fallEventDao = null;
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Completable saveEvent(@org.jetbrains.annotations.NotNull()
    com.tuhin.skfreefall.data.model.FreeFallData user) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<java.util.List<com.tuhin.skfreefall.data.model.FreeFallData>> getAllEvent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Completable deleteAllEvent() {
        return null;
    }
    
    public final void closeDataSource() {
    }
    
    public FreeFallRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
}