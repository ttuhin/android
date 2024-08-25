package com.tuhin.skfreefall.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * This is a model class to hold free fall information
 *
 *@param timeStamp The time in millisecond at which the free fall happened
 *@param duration The duration of the free fall in milliseconds
 */
@Entity(tableName = "FreeFallData")
data class FreeFallData(@PrimaryKey(autoGenerate = false)  var timeStamp : String,
                         var duration : String)

