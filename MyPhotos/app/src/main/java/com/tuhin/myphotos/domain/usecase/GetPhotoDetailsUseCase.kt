package com.tuhin.myphotos.domain.usecase

import com.tuhin.myphotos.data.remote.model.Album
import com.tuhin.myphotos.domain.model.PhotoDetails
import com.tuhin.myphotos.data.remote.model.Photo
import com.tuhin.myphotos.data.remote.model.User
import com.tuhin.myphotos.domain.repository.JsonPlaceholderRepository

import android.util.Log

import java.util.*
import javax.inject.Inject

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Function3
import io.reactivex.rxjava3.schedulers.Schedulers

private const val TAG = "GetPhotoDetailsUseCase"

/**
 * It is photo details use case which can provide
 * photo details Observable data
 *
 * @property jsonPlaceholderRepository the injected repository
 * this property must be injected
 * @constructor Creates a use case
 */
class GetPhotoDetailsUseCase @Inject constructor(private val jsonPlaceholderRepository: JsonPlaceholderRepository) {
    /**
     * Usecase method to get observable photo details list
     *
     * @return observable list of photo details
     */
    operator fun invoke(): Observable<List<PhotoDetails>> {
        return Observable.zip(
            jsonPlaceholderRepository.getUsers()
                .subscribeOn(Schedulers.io()),
            jsonPlaceholderRepository.getAlbums()
                .subscribeOn(Schedulers.io()),
            jsonPlaceholderRepository.getPhotos()
                .subscribeOn(Schedulers.io()),
            Function3 { users, albums, photos ->
                return@Function3 getPhotoDetailsList(users, albums, photos)
            })
    }

    /**
     * Construct photo details list from users,albums and photos
     * @param users the list of user
     * @param albums the list of album
     * @param photos the list of photos
     * @return list of photo details
     */
    private fun getPhotoDetailsList(
        users: List<User>,
        albums: List<Album>, photos: List<Photo>
    ): List<PhotoDetails> {
        val photoDetailsList = ArrayList<PhotoDetails>()

        for (photo in photos) {
            val album = albums.find {
               it.id ==  photo.albumId
            }

            val user = users.find {
                it.id == album?.userId
            }

            val photoDetails = PhotoDetails(
                photoId = photo.id,
                photoTitle = photo.title, albumID = photo.albumId,  albumName = album?.title!!,
                userName = user?.userName!!, thumbnailUrl = photo.thumbnailUrl
            )
            photoDetailsList.add(photoDetails)
        }

        Log.d(TAG, "$photoDetailsList")

        return photoDetailsList
    }
}