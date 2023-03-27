package com.tuhin.mymovies.domain.usecase

import com.tuhin.mymovies.data.Resource
import com.tuhin.mymovies.data.remote.model.toMovie
import com.tuhin.mymovies.domain.model.Movie
import com.tuhin.mymovies.domain.repository.MovieRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieUseCase @Inject constructor (private val movieRepository : MovieRepository) {

    operator fun invoke() : Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = movieRepository.getPopularMovies().movies.map {
                it.toMovie()
            }
            emit(Resource.Success(movieList))
        } catch (ex: HttpException) {
            emit(Resource.Error(ex.message() ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Check your network connection."))
        }
    }
}