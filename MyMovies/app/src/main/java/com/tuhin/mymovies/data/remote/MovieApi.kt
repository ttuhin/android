package com.tuhin.mymovies.data.remote

import com.tuhin.mymovies.data.remote.model.MovieDto
import com.tuhin.mymovies.data.remote.model.PaginatedMovieDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") query: String? = null,
        @Query("page") page: Int? = null
    ): PaginatedMovieDto

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Long,
        @Query("language") query: String? = null
    ): MovieDto
}