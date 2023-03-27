package com.tuhin.myphotos.data.remote

import com.tuhin.myphotos.data.remote.model.Album
import com.tuhin.myphotos.data.remote.model.Photo
import com.tuhin.myphotos.data.remote.model.User

import io.reactivex.rxjava3.core.Observable

import retrofit2.http.GET

/**
 * Retrofit JsonPlaceholderApi class
 */
interface JsonPlaceholderApi {
    /**
     * Get photos from JsonPlaceholder web service
     * @return observable list of photo
     */
    @GET("photos")
    fun getPhotos(): Observable<List<Photo>>

    /**
     * Get albums from JsonPlaceholder web service
     * @return observable list of album
     */
    @GET("albums")
    fun getAlbums(): Observable<List<Album>>

    /**
     * Get users from JsonPlaceholder web service
     * @return observable list of user
     */
    @GET("users")
    fun getUsers(): Observable<List<User>>
}