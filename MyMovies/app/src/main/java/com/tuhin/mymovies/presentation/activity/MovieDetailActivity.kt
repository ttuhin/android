package com.tuhin.mymovies.presentation.activity

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle

import com.tuhin.mymovies.R
import com.tuhin.mymovies.constant.RemoteConstant
import com.tuhin.mymovies.constant.ViewConstant
import com.tuhin.mymovies.constant.ViewConstant.DATE_FORMAT
import com.tuhin.mymovies.databinding.ActivityMovieDetailBinding
import com.tuhin.mymovies.domain.model.Movie

import com.bumptech.glide.Glide

import java.util.*

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        supportActionBar?.title = resources.getString(R.string.detail)

        val movie: Movie? = intent.extras?.get(ViewConstant.MOVIE_KEY) as Movie?
        movie?.let {
            initViews(it)
        }
    }

    private fun initViews(movie:Movie) {
        with(viewBinding) {
            Glide.with(this@MovieDetailActivity)
                .load("${RemoteConstant.IMAGE_BASE_URL}${movie.posterPath}")
                .into(posterImage)
            titleText.text = movie.title
            ratingText.text = movie.rating.toString()
            overViewText.text = movie.overView
            releaseDateText.text = android.text.format.DateFormat.format(DATE_FORMAT, movie.releaseDate)
        }
    }
}