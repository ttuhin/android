package com.tuhin.skfreefall.util

import android.text.format.DateFormat

/**
 * This is a util class for date time related conversion
 */
object TimeUtil {

    /**
     * Convert milliseconds time stamp into date time times tamp
     *
     * @param timeInMilliseconds The time stamp in millisecond
     * @return time stamp in date time in 'yyyy-MM-dd HH:mm:ss.mmm' format
     */
    fun getTimeStampInDateTimeFormat(timeInMilliseconds: Long) : String {
        return DateFormat.format("yyyy-MM-dd HH:mm:ss.mmm", timeInMilliseconds).toString()
    }
}