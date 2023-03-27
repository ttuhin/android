package com.tuhin.mymovies.domain.usecase

import com.tuhin.mymovies.data.Resource
import com.tuhin.mymovies.data.remote.model.MovieDto
import com.tuhin.mymovies.data.remote.model.PaginatedMovieDto
import com.tuhin.mymovies.data.repository.MovieRepositoryImp
import com.tuhin.mymovies.domain.repository.MovieRepository

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import junit.framework.TestCase.*

import org.junit.After
import org.junit.Before
import org.junit.Test

import java.util.*

/*
Test MovieUseCase
 */

private const val IO_EXCEPTION_MESSAGE = "Check your network connection."

class MovieUseCaseTest {

    private val movieDto = MovieDto(
        adult = true,
        backdropPath = "",
        genreIds = emptyList(),
        id = 1,
        originalLanguage = "English",
        originalTitle = "test title",
        overview = "test overview",
        popularity = 6.7,
        posterPath = "test path",
        releaseDate = Calendar.getInstance().time,
        title = "test title",
        video = true,
        rating = 4.0,
        voteCount = 10
    )

    private val movies = arrayListOf(movieDto)

    private val paginatedMovieDto = PaginatedMovieDto(
        page = 1,
        movies = movies,
        totalPages = 10,
        totalResults = 12
    )

    @RelaxedMockK
    private lateinit var movieRepository: MovieRepository

    @Before
    fun setUp() {
        movieRepository = mockk<MovieRepositoryImp>()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `get popular movies, movie list with success state`() = runBlocking {
        coEvery { movieRepository.getPopularMovies() } returns paginatedMovieDto
        GetMovieUseCase(movieRepository)().collect {
            when (it) {
                is Resource.Success -> {
                    val movieData = it.data?.get(0)
                    assertEquals(movieData?.id, movieDto.id)
                    assertEquals(movieData?.title, movieDto.title)
                    assertEquals(movieData?.posterPath, movieDto.posterPath)
                    assertEquals(movieData?.releaseDate, movieDto.releaseDate)
                    assertEquals(movieData?.overView, movieDto.overview)
                    assertEquals(movieData?.rating!!, movieDto.rating, 0.0)
                }
                is Resource.Loading-> {
                    assertTrue(true)
                }
                else -> {
                    assertFalse(true)
                }
            }
        }
    }

    @Test
    fun `get popular movies with io exception, Check your network connection error with null data`() = runBlocking {
        coEvery { movieRepository.getPopularMovies() } throws IOException()
        GetMovieUseCase(movieRepository)().collect {
            when (it) {
                is Resource.Error -> {
                    assertEquals(IO_EXCEPTION_MESSAGE, it.message)
                    assertNull(it.data)
                }
                is Resource.Loading-> {
                    assertTrue(true)
                }
                else -> {
                    assertFalse(true)
                }
            }
        }
    }

    @Test
    fun `get popular movies with http exception, error with null data`()  = runBlocking {
        val errorResponse = "errorResponse"
        val errorResponseBody = errorResponse.toResponseBody("application/json".toMediaTypeOrNull())

        coEvery { movieRepository.getPopularMovies() } throws HttpException(Response.error<String>(400, errorResponseBody))

        GetMovieUseCase(movieRepository)().collect {
            when (it) {
                is Resource.Error -> {
                    assertTrue(true)
                    assertNull(it.data)
                }
                is Resource.Loading -> {
                    assertTrue(true)
                }
                else -> {
                    assertFalse(true)
                }
            }
        }
    }
}