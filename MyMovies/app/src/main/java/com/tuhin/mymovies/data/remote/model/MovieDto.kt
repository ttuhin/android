package com.tuhin.mymovies.data.remote.model

import com.google.gson.annotations.SerializedName
import com.tuhin.mymovies.domain.model.Movie
import java.util.*

data class MovieDto(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: Date,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val rating: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)

fun MovieDto.toMovie() : Movie {
    return Movie(
        id = id,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate,
        overView = overview,
        rating = rating
    )
}