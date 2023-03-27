package com.tuhin.mymovies.domain.repository

import com.tuhin.mymovies.data.remote.model.MovieDto
import com.tuhin.mymovies.data.remote.model.PaginatedMovieDto

interface MovieRepository {

    suspend fun getPopularMovies() : PaginatedMovieDto
    suspend fun getMovieByID(id : Long) : MovieDto
}