package com.tuhin.mymovies.data.remote.model

import com.google.gson.annotations.SerializedName

data class PaginatedMovieDto(
    val page: Int,
    @SerializedName("results")
    val movies: List<MovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)