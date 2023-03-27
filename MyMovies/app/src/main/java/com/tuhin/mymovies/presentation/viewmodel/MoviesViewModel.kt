package com.tuhin.mymovies.presentation.viewmodel

import androidx.lifecycle.*
import com.tuhin.mymovies.data.Resource
import com.tuhin.mymovies.domain.model.Movie

import com.tuhin.mymovies.domain.usecase.GetMovieUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesUseCase: GetMovieUseCase) : ViewModel() {
    private val _movieList = MutableLiveData<Resource<List<Movie>>>()

    val movieList : LiveData<Resource<List<Movie>>>
    get() = _movieList

   fun getMovies() {
        moviesUseCase().onEach {
            when (it) {
                is Resource.Success-> {
                    _movieList.value = Resource.Success (it.data ?: emptyList())
                }
                is Resource.Error-> {
                    _movieList.value = Resource.Error (it.message ?: "")
                }
                is Resource.Loading-> {
                    _movieList.value = Resource.Loading()
                }
            }
        }.launchIn(viewModelScope)
    }
}