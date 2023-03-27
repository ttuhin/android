package com.tuhin.myphotos.data.repository

import com.tuhin.myphotos.data.remote.JsonPlaceholderApi
import com.tuhin.myphotos.data.remote.model.Album
import com.tuhin.myphotos.data.remote.model.Photo
import com.tuhin.myphotos.data.remote.model.User

import com.tuhin.myphotos.domain.repository.JsonPlaceholderRepository

import io.reactivex.rxjava3.core.Observable

import javax.inject.Inject

/**
 * It is a repository for JsonPlaceHolder API which exposes
 * photo details Observable data
 *
 * @property jsonPlaceholderApi the injected json place holder API
 * this property must be injected
 * @constructor Creates a repository
 */
class JsonPlaceholderRepositoryImp  @Inject constructor(private val jsonPlaceholderApi : JsonPlaceholderApi) : JsonPlaceholderRepository {
    override fun getPhotos(): Observable<List<Photo>> {
        return jsonPlaceholderApi.getPhotos()
    }

    override fun getAlbums(): Observable<List<Album>> {
        return jsonPlaceholderApi.getAlbums()
    }

    override fun getUsers(): Observable<List<User>> {
        return jsonPlaceholderApi.getUsers()
    }
}