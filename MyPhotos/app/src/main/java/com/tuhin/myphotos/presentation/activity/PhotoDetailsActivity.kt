package com.tuhin.myphotos.presentation.activity

import com.tuhin.myphotos.presentation.adapter.PhotoDetailsAdapter
import com.tuhin.myphotos.presentation.viewmodel.PhotoDetailsViewModel
import com.tuhin.myphotos.databinding.ActivityPhotoDetailsBinding

import android.os.Bundle

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import dagger.hilt.android.AndroidEntryPoint

import javax.inject.Inject

import com.google.android.material.snackbar.Snackbar

/**
 * Main entry point for the app
 * It displays one photo from each album for all user
 */
@AndroidEntryPoint
class PhotoDetailsActivity : AppCompatActivity() {
    private val photoDetailsViewModel: PhotoDetailsViewModel by viewModels()
    private lateinit var viewBinding : ActivityPhotoDetailsBinding

    @Inject
    lateinit var photoDetailsAdapter: PhotoDetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityPhotoDetailsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initMovieListView()
        initSwipeView()
        observePhoDetailsList()
    }

    private fun initSwipeView() {
        viewBinding.photoDetailsListSwipeRefresh.setOnRefreshListener {
            photoDetailsViewModel.getPhotos()
        }
    }

    private fun initMovieListView() {
        with(photoDetailsAdapter) {
            setHasStableIds(true)
        }
        viewBinding.photoDetailsListView.adapter = photoDetailsAdapter
    }

    private fun observePhoDetailsList() {
        photoDetailsViewModel.photoDetailsList.observe(this, {
            when (it) {
                is com.tuhin.myphotos.data.Result.Success-> {
                    photoDetailsAdapter.setMovieList(it.data!!)
                    viewBinding.photoDetailsListSwipeRefresh.isRefreshing = false
                }
                is com.tuhin.myphotos.data.Result.Error-> {
                    val snackBar = Snackbar.make(
                        viewBinding.root,
                        it.message!!,
                        Snackbar.LENGTH_LONG
                    )
                    snackBar.show()
                    viewBinding.photoDetailsListSwipeRefresh.isRefreshing = false
                }
                is com.tuhin.myphotos.data.Result.Loading-> {
                    viewBinding.photoDetailsListSwipeRefresh.isRefreshing = true
                }
            }
        })
    }
}