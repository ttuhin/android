package com.tuhin.mymovies.presentation.activity

import android.content.Intent
import android.os.Bundle

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import com.tuhin.mymovies.constant.ViewConstant
import com.tuhin.mymovies.data.Resource

import com.tuhin.mymovies.databinding.ActivityMovieListBinding
import com.tuhin.mymovies.domain.model.Movie
import com.tuhin.mymovies.presentation.adapter.MovieAdapter
import com.tuhin.mymovies.presentation.viewmodel.MoviesViewModel

import dagger.hilt.android.AndroidEntryPoint

import javax.inject.Inject

import com.google.android.material.snackbar.Snackbar

//import com.tuhin.mymovies.R

import android.R
import android.view.Gravity
import android.widget.LinearLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout

@AndroidEntryPoint
class MovieListActivity : AppCompatActivity() {
    private val moviesViewModel: MoviesViewModel by viewModels()
    private lateinit var viewBinding : ActivityMovieListBinding

    @Inject
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initMovieListView()
        initSwipeView()
        moviesViewModel.getMovies()
        observeMovieList()
    }

    private fun initSwipeView() {
        viewBinding.movieListSwipeRefresh.setOnRefreshListener {
            moviesViewModel.getMovies()
        }
    }

    private fun initMovieListView() {
        with(movieAdapter) {
            setHasStableIds(true)
            selectedMovie.observe(this@MovieListActivity, {
                goToDetailPage(it)
            })
        }
        viewBinding.movieListView.adapter = movieAdapter
    }

    private fun observeMovieList() {
        moviesViewModel.movieList.observe(this, {
            when (it) {
                is Resource.Success-> {
                    it.data?.let {movieList ->
                        movieAdapter.setMovieList(movieList)
                    }
                    viewBinding.movieListSwipeRefresh.isRefreshing = false
                }
                is Resource.Error-> {
                    viewBinding.movieListSwipeRefresh.isRefreshing = false
                    displayError(it.message)
                }
                is Resource.Loading-> {
                    viewBinding.movieListSwipeRefresh.isRefreshing = true
                }
            }
        })
    }

    private fun goToDetailPage(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java).apply {
            putExtra(ViewConstant.MOVIE_KEY, movie)
        }
        startActivity(intent)
    }

    private fun displayError(message : String?) {
        Snackbar.make(
            findViewById(R.id.content),
            message?: "Unknown Error",
            Snackbar.LENGTH_LONG
        ).show()
    }
}