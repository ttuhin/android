package com.tuhin.myphotos.data.repository

import com.tuhin.myphotos.TestConstant.NETWORK_ERROR
import com.tuhin.myphotos.data.remote.JsonPlaceholderApi
import com.tuhin.myphotos.data.remote.model.*
import com.tuhin.myphotos.domain.repository.JsonPlaceholderRepository

import java.util.concurrent.TimeUnit

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.rxjava3.core.Observable

import org.junit.Before
import org.junit.Test

/**
 * Test class for JsonPlaceholderRepositoryImp
 */
class JsonPlaceholderRepositoryImpTest {

    private val geo = Geo(lat = "test_lat", lng = "test_lng")
    private val address = Address(
        city = "test_city",
        geo, street = "test_street", suite = "test_suite", zipCode = "test_zip_code"
    )
    private val company = Company(
        bs = "test_bs", catchPhrase = "test_catch_phrase",
        name = "test_name"
    )
    private val user = User(
        address = address, company = company, email = "test_email", id = 1,
        name = "test_name", phone = "123456", userName = "test_user_name", website = "test_website"
    )
    private val album = Album(id = 1, title = "test_title", userId = 1)
    private val photo = Photo(
        albumId = 1,
        id = 1,
        thumbnailUrl = "test_url",
        title = "test_title",
        url = "test_url"
    )

    @RelaxedMockK
    private lateinit var jsonPlaceholderApi: JsonPlaceholderApi
    private lateinit var jsonPlaceholderRepository: JsonPlaceholderRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        jsonPlaceholderRepository = JsonPlaceholderRepositoryImp(jsonPlaceholderApi)
    }

    @Test
    fun `get user list, user list with success`() {
        every {
            jsonPlaceholderApi.getUsers()
        } returns Observable.just(arrayListOf(user))

        jsonPlaceholderRepository.getUsers()
            .test()
            .awaitDone(100, TimeUnit.MILLISECONDS)
            .assertValueCount(1)
            .assertValue {
                it[0] == user
            }
    }

    @Test
    fun `get user list, should return error`() {
        every {
            jsonPlaceholderApi.getUsers()
        } returns Observable.error(Throwable(NETWORK_ERROR))

        jsonPlaceholderRepository.getUsers()
            .test()
            .awaitDone(100, TimeUnit.MILLISECONDS)
            .assertValueCount(0)
            .assertError {
                it.message == NETWORK_ERROR
            }
    }

    @Test
    fun `get album list, album list with success`() {
        every {
            jsonPlaceholderApi.getAlbums()
        } returns Observable.just(arrayListOf(album))

        jsonPlaceholderRepository.getAlbums()
            .test()
            .awaitDone(100, TimeUnit.MILLISECONDS)
            .assertValueCount(1)
            .assertValue {
                it[0] == album
            }
    }

    @Test
    fun `get album list, should return error`() {
        every {
            jsonPlaceholderApi.getAlbums()
        } returns Observable.error(Throwable(NETWORK_ERROR))

        jsonPlaceholderRepository.getAlbums()
            .test()
            .awaitDone(100, TimeUnit.MILLISECONDS)
            .assertValueCount(0)
            .assertError {
                it.message == NETWORK_ERROR
            }
    }

    @Test
    fun `get photo list, photo list with success`() {
        every {
            jsonPlaceholderApi.getPhotos()
        } returns Observable.just(arrayListOf(photo))

        jsonPlaceholderRepository.getPhotos()
            .test()
            .awaitDone(100, TimeUnit.MILLISECONDS)
            .assertValueCount(1)
            .assertValue {
                it[0] == photo
            }
    }

    @Test
    fun `get photo list, should return error`() {
        every {
            jsonPlaceholderApi.getPhotos()
        } returns Observable.error(Throwable(NETWORK_ERROR))

        jsonPlaceholderRepository.getPhotos()
            .test()
            .awaitDone(100, TimeUnit.MILLISECONDS)
            .assertValueCount(0)
            .assertError {
                it.message == NETWORK_ERROR
            }
    }
}