package com.tuhin.skfreefall.data.database;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\ba\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\'\u00a8\u0006\t"}, d2 = {"Lcom/tuhin/skfreefall/data/database/FallEventDao;", "", "getAll", "Lio/reactivex/Observable;", "", "Lcom/tuhin/skfreefall/data/model/FreeFallData;", "save", "Lio/reactivex/Completable;", "event", "skfreefall_debug"})
public abstract interface FallEventDao {
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Insert()
    public abstract io.reactivex.Completable save(@org.jetbrains.annotations.NotNull()
    com.tuhin.skfreefall.data.model.FreeFallData event);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM FreeFallData")
    public abstract io.reactivex.Observable<java.util.List<com.tuhin.skfreefall.data.model.FreeFallData>> getAll();
}