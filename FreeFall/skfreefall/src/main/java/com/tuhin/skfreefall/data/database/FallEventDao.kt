package com.tuhin.skfreefall.data.database

import com.tuhin.skfreefall.data.model.FreeFallData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

import io.reactivex.Completable
import io.reactivex.Observable

@Dao
internal interface FallEventDao {

    @Insert
    fun save(event: FreeFallData): Completable

    @Query("SELECT * FROM FreeFallData")
    fun getAll(): Observable<List<FreeFallData>>

}