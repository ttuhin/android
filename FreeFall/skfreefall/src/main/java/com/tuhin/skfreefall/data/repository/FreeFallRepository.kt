package com.tuhin.skfreefall.data.repository

import com.tuhin.skfreefall.data.database.FallDetectorDatabase
import com.tuhin.skfreefall.data.database.FallEventDao
import com.tuhin.skfreefall.data.model.FreeFallData

import android.content.Context

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

internal class FreeFallRepository(context: Context) {

    private val database = FallDetectorDatabase.getAppDataBase(context.applicationContext)!!
    private val fallEventDao: FallEventDao = database.fallEventDao()

    fun saveEvent(user: FreeFallData) : Completable {
        return fallEventDao.save(user).subscribeOn(Schedulers.io())
    }

    fun getAllEvent(): Observable<List<FreeFallData>> {
          return fallEventDao.getAll()
              .subscribeOn(Schedulers.io())
    }

    fun deleteAllEvent() : Completable {
        return Completable.fromAction {
            database.clearAllTables()
        }.subscribeOn(Schedulers.io())
    }

    fun closeDataSource() {
        FallDetectorDatabase.destroyDataBase()
    }
}