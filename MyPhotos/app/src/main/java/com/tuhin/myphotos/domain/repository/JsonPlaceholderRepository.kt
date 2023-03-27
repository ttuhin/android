package com.tuhin.myphotos.domain.repository

import com.tuhin.myphotos.data.remote.model.Album
import com.tuhin.myphotos.data.remote.model.Photo
import com.tuhin.myphotos.data.remote.model.User

import io.reactivex.rxjava3.core.Observable

/**
 * This is an interface for data repository
 * It displays one photo from each album for all user
 */
interface JsonPlaceholderRepository {

    /**
     * Fetch photos of users
     * @return the observable list of photos
     */
    fun getPhotos(): Observable<List<Photo>>

    /**
     * Fetch  of users
     * @return the observable list of albu
     */
    fun getAlbums(): Observable<List<Album>>

    /**
     * Fetch users
     * @return the observable list of user
     */
    fun getUsers(): Observable<List<User>>
}