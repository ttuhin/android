package com.tuhin.myphotos.data

/**
 * Holds the state of an operation
 * like database and network operation
 * @param data any type of data
 * @param message error message
 */
sealed class Result<T>(val data:T? = null, val message:String?=null) {
    /**
     * Holds the success state and data of an operation
     * @param data any type of data
     */
    class Success<T>(data: T) : Result<T>(data)
    /**
     * Holds the error state of an operation
     * @param message error message
     * @param data any type of data
     */
    class Error<T>(message: String, data: T? = null) : Result<T>(data, message)
    /**
     * Holds the running state of an operation
     * @param data any type of data
     */
    class Loading<T>(data: T? = null) : Result<T>(data)
}