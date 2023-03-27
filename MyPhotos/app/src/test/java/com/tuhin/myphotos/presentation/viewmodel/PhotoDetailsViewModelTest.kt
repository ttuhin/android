package com.tuhin.myphotos.presentation.viewmodel

import com.tuhin.myphotos.data.Result
import com.tuhin.myphotos.domain.model.PhotoDetails
import com.tuhin.myphotos.domain.usecase.GetPhotoDetailsUseCase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.tuhin.myphotos.TestConstant.ALBUM_NAME
import com.tuhin.myphotos.TestConstant.NETWORK_ERROR
import com.tuhin.myphotos.TestConstant.PHOTO_TITLE
import com.tuhin.myphotos.TestConstant.PHOTO_TITLE2
import com.tuhin.myphotos.TestConstant.THUMBNAIL_URL
import com.tuhin.myphotos.TestConstant.USER_NAME
import com.tuhin.myphotos.constant.RemoteConstant.ERROR_MESSAGE

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.internal.schedulers.ExecutorScheduler
import io.reactivex.rxjava3.plugins.RxJavaPlugins

import org.junit.*
import org.junit.Assert.*
import org.junit.rules.TestRule

class PhotoDetailsViewModelTest {

    private val photoDetails = PhotoDetails(
        photoId = 1,
        photoTitle = PHOTO_TITLE, albumID = 1, albumName = ALBUM_NAME,
        userName =  USER_NAME, thumbnailUrl = THUMBNAIL_URL
    )

    private val photoDetails2 = PhotoDetails(
        photoId = 2,
        photoTitle = PHOTO_TITLE2, albumID = 1, albumName = ALBUM_NAME,
        userName =  USER_NAME, thumbnailUrl = THUMBNAIL_URL
    )

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val photoDetailsList = arrayListOf(photoDetails, photoDetails2)
    @RelaxedMockK
    private lateinit var photoDetailsUseCase : GetPhotoDetailsUseCase
    @RelaxedMockK
    private lateinit var photoDetailsObserver: Observer<Result<List<PhotoDetails>>>
    private lateinit var photoDetailsViewModel : PhotoDetailsViewModel

    companion object {
        @BeforeClass
        @JvmStatic fun setUpClass() {
            val immediate = object : Scheduler() {
                override fun createWorker(): Worker {
                    return ExecutorScheduler.ExecutorWorker({ it.run() }, true, true)
                }
            }

            RxJavaPlugins.setInitIoSchedulerHandler { immediate }
            RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
            RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
            RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
        }

        @AfterClass
        @JvmStatic fun resetClass() {
            RxJavaPlugins.reset()
            RxAndroidPlugins.reset()
        }
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        photoDetailsViewModel = PhotoDetailsViewModel(photoDetailsUseCase)
    }

    @Test
    fun `get photo details , should return success with emitted data`()  {
        every {
            photoDetailsUseCase()
        } returns Observable.just(photoDetailsList)

        photoDetailsViewModel.apply {
            getPhotos()
            photoDetailsList.observeForever(photoDetailsObserver)
        }

        val result = mutableListOf<Result<List<PhotoDetails>>>()

        verify {
            photoDetailsObserver.onChanged(capture(result))
        }

        when(result[0]) {
            is Result.Success-> {
                assertEquals(PHOTO_TITLE, result[0].data?.get(0)?.photoTitle)
                assertEquals(ALBUM_NAME, result[0].data?.get(0)?.albumName)
                assertEquals(USER_NAME, result[0].data?.get(0)?.userName)
                assertEquals(THUMBNAIL_URL, result[0].data?.get(0)?.thumbnailUrl)
            }
            is Result.Error-> {
                assertFalse(true)
            }
            is Result.Loading-> {
                assertTrue(true)
            }
        }
    }

    @Test
    fun `get photo details, should return one photo from each album`()  {
        every {
            photoDetailsUseCase()
        } returns Observable.just(photoDetailsList)

        photoDetailsViewModel.apply {
            getPhotos()
            photoDetailsList.observeForever(photoDetailsObserver)
        }

        val result = mutableListOf<Result<List<PhotoDetails>>>()

        verify {
            photoDetailsObserver.onChanged(capture(result))
        }

        when(result[0]) {
            is Result.Success-> {
                assertTrue(result.size == 1)
                assertEquals(PHOTO_TITLE, result[0].data?.get(0)?.photoTitle)
                assertEquals(ALBUM_NAME, result[0].data?.get(0)?.albumName)
                assertEquals(USER_NAME, result[0].data?.get(0)?.userName)
                assertEquals(THUMBNAIL_URL, result[0].data?.get(0)?.thumbnailUrl)
            }
            is Result.Error-> {
                assertFalse(true)
            }
            is Result.Loading-> {
                assertTrue(true)
            }
        }
    }

    @Test
    fun `get photo details , should emit error`()  {
        every {
            photoDetailsUseCase()
        } returns Observable.error(Throwable(NETWORK_ERROR))

        photoDetailsViewModel.apply {
            getPhotos()
            photoDetailsList.observeForever(photoDetailsObserver)
        }

        val result = mutableListOf<Result<List<PhotoDetails>>>()

        verify {
            photoDetailsObserver.onChanged(capture(result))
        }

        when(result[0]) {
            is Result.Success-> {
                assertFalse(true)
            }
            is Result.Error-> {
                assertTrue(true)
                assertEquals(ERROR_MESSAGE, result[0].message)
            }
            is Result.Loading-> {
                assertTrue(true)
            }
        }
    }
}