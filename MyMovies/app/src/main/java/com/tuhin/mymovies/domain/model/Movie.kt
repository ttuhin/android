package com.tuhin.mymovies.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val posterPath : String,
    val releaseDate: Date,
    val overView: String,
    val rating: Double
    ) : Parcelable
