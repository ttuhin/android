package com.tuhin.mymovies.data.repository

import com.tuhin.mymovies.data.remote.MovieApi
import com.tuhin.mymovies.data.remote.model.MovieDto
import com.tuhin.mymovies.data.remote.model.PaginatedMovieDto

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking

import org.junit.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test

import java.util.*

/*
This is a unit test class for MovieRepositoryImp
 */
class MovieRepositoryImpTest {

    private val paginatedMovieDto = PaginatedMovieDto(
        page = 1,
        movies = emptyList(),
        totalPages = 1,
        totalResults = 1
    )

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


    @RelaxedMockK
    private lateinit var movieApi: MovieApi
    private lateinit var movieRepository: MovieRepositoryImp

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        movieApi = mockk()
        movieRepository = MovieRepositoryImp(movieApi)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `get popular movies , paginated movie list`() = runBlocking {
        coEvery { movieApi.getPopularMovies() } returns paginatedMovieDto
        val paginatedMovieDtoResult = movieRepository.getPopularMovies()
        assertEquals(paginatedMovieDtoResult.page, paginatedMovieDto.page)
        assertEquals(paginatedMovieDtoResult.totalPages, paginatedMovieDto.totalPages)
        assertEquals(paginatedMovieDtoResult.totalResults, paginatedMovieDto.totalResults)
        assertTrue(paginatedMovieDtoResult.movies.isEmpty())
    }

    @Test
    fun `get movie by id , paginated movie`() = runBlocking {
        coEvery { movieApi.getMovieById(1) } returns movieDto
        val movieDtoResult = movieRepository.getMovieByID(1)
        assertEquals(movieDtoResult.id, movieDto.id)
        assertEquals(movieDtoResult.originalLanguage, movieDto.originalLanguage)
        assertEquals(movieDtoResult.originalTitle, movieDto.originalTitle)
    }
}