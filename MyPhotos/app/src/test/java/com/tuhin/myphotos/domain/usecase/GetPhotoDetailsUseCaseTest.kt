package com.tuhin.myphotos.domain.usecase

import com.tuhin.myphotos.TestConstant
import com.tuhin.myphotos.data.remote.model.*
import com.tuhin.myphotos.data.repository.JsonPlaceholderRepositoryImp

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.rxjava3.core.Observable

import org.junit.Before
import org.junit.Test

import java.util.concurrent.TimeUnit

/**
 * Test class for GetPhotoDetailsUseCase
 */
class GetPhotoDetailsUseCaseTest {

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
        name = "test_name", phone = "123456", TestConstant.USER_NAME, website = "test_website"
    )
    private val album = Album(id = 1, title = TestConstant.ALBUM_NAME, userId = 1)
    private val photo = Photo(
        albumId = 1,
        id = 1,
        TestConstant.THUMBNAIL_URL,
        TestConstant.PHOTO_TITLE,
        url = "test_url"
    )

    @RelaxedMockK
    private lateinit var jsonPlaceholderRepository: JsonPlaceholderRepositoryImp

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `get photo details, photo details list with success`() {
        every {
            jsonPlaceholderRepository.getUsers()
        } returns Observable.just(arrayListOf(user))

        every {
            jsonPlaceholderRepository.getAlbums()
        } returns Observable.just(arrayListOf(album))

        every {
            jsonPlaceholderRepository.getPhotos()
        } returns Observable.just(arrayListOf(photo))

        GetPhotoDetailsUseCase(jsonPlaceholderRepository)()
            .test()
            .awaitDone(1, TimeUnit.SECONDS)
            .assertValueCount(1)
            .assertValue {
                it[0].userName == TestConstant.USER_NAME &&
                it[0].albumName == TestConstant.ALBUM_NAME &&
                it[0].photoTitle == TestConstant.PHOTO_TITLE &&
                it[0].thumbnailUrl == TestConstant.THUMBNAIL_URL
            }
    }

    @Test
    fun `get photo details, should emit error`() {
        every {
            jsonPlaceholderRepository.getUsers()
        } returns Observable.error(Throwable(TestConstant.UNKNOWN_ERROR))

        every {
            jsonPlaceholderRepository.getAlbums()
        } returns Observable.error(Throwable(TestConstant.UNKNOWN_ERROR))

        every {
            jsonPlaceholderRepository.getPhotos()
        } returns Observable.error(Throwable(TestConstant.UNKNOWN_ERROR))

        GetPhotoDetailsUseCase(jsonPlaceholderRepository)()
            .test()
            .awaitDone(100, TimeUnit.MILLISECONDS)
            .assertValueCount(0)
            .assertError {
                it.message == TestConstant.UNKNOWN_ERROR
            }
    }
}