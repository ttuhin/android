package com.tuhin.skfreefall.data.database

import com.tuhin.skfreefall.data.model.FreeFallData

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FreeFallData::class], version = 1, exportSchema = false)
internal abstract class FallDetectorDatabase : RoomDatabase() {

    abstract fun fallEventDao(): FallEventDao

    companion object {
        private var instance: FallDetectorDatabase? = null

        fun getAppDataBase(context: Context): FallDetectorDatabase? {
            if (instance == null) {
                synchronized(FallDetectorDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext, FallDetectorDatabase::class.java, "FallDetector").build()
                }
            }
            return instance
        }

        fun destroyDataBase() {
            instance?.close()
            instance = null
        }
    }

}