package com.tuhin.mymovies.data.repository

import com.tuhin.mymovies.data.remote.model.MovieDto
import com.tuhin.mymovies.data.remote.MovieApi
import com.tuhin.mymovies.data.remote.model.PaginatedMovieDto

import com.tuhin.mymovies.domain.repository.MovieRepository

import javax.inject.Inject

class MovieRepositoryImp  @Inject constructor(private val movieApi : MovieApi) : MovieRepository {
    override suspend fun getPopularMovies(): PaginatedMovieDto {
        return movieApi.getPopularMovies()
    }

    override suspend fun getMovieByID(id : Long): MovieDto {
        return movieApi.getMovieById(id)
    }
}