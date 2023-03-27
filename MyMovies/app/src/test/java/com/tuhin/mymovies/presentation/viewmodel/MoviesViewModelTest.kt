package com.tuhin.mymovies.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.tuhin.mymovies.data.Resource
import com.tuhin.mymovies.domain.model.Movie
import com.tuhin.mymovies.domain.usecase.GetMovieUseCase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.setMain

import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.junit.rules.TestRule

import java.util.*

private const val ERROR_MESSAGE = "An unexpected error occurred"

class MoviesViewModelTest {

    @get:Rule var rule: TestRule = InstantTaskExecutorRule()

    private val movie = Movie(
        id = 1,
        title = "test title",
        posterPath = "test poster path",
        releaseDate = Calendar.getInstance().time,
        overView = "test ",
        rating = 9.0
    )

    private val movies = arrayListOf(movie)

    @RelaxedMockK
    private lateinit var movieUseCase : GetMovieUseCase
    @RelaxedMockK
    private lateinit var movieObserver: Observer<Resource<List<Movie>>>
    private lateinit var moviesViewModel : MoviesViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        moviesViewModel = MoviesViewModel(movieUseCase)
        Dispatchers.setMain(Dispatchers.IO)
    }

    @Test
    fun `get movies, should return success with data`() = runBlocking {

        val dataFlow = flow<Resource<List<Movie>>> {
            emit(Resource.Success(movies))
        }

        every {
            movieUseCase()
        } returns dataFlow

        moviesViewModel.getMovies()
        moviesViewModel.movieList.observeForever(movieObserver)
        val response = mutableListOf<Resource<List<Movie>>>()

        verify {
            movieObserver.onChanged(capture(response))
        }

        assertEquals(movies[0].id, response[0].data?.get(0)?.id)
        assertEquals(movies[0].title, response[0].data?.get(0)?.title)
        assertEquals(movies[0].releaseDate, response[0].data?.get(0)?.releaseDate)
        assertEquals(movies[0].posterPath, response[0].data?.get(0)?.posterPath)
        assertEquals(movies[0].overView, response[0].data?.get(0)?.overView)
        assertEquals(movies[0].rating, response[0].data?.get(0)?.rating)
    }

    @Test
    fun `get movies with error, should return error`() = runBlocking {

        val dataFlow = flow<Resource<List<Movie>>> {
            emit(Resource.Error(ERROR_MESSAGE))
        }

        every {
            movieUseCase()
        } returns dataFlow

        moviesViewModel.getMovies()
        moviesViewModel.movieList.observeForever(movieObserver)
        val response = mutableListOf<Resource<List<Movie>>>()

        verify {
            movieObserver.onChanged(capture(response))
        }

        assertEquals(ERROR_MESSAGE, response[0].message)
    }
}
