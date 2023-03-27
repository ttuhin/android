package com.tuhin.myphotos.presentation.viewmodel

import android.util.Log
import com.tuhin.myphotos.data.Result
import com.tuhin.myphotos.constant.RemoteConstant.ERROR_MESSAGE

import com.tuhin.myphotos.domain.model.PhotoDetails
import com.tuhin.myphotos.domain.usecase.GetPhotoDetailsUseCase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import javax.inject.Inject

import dagger.hilt.android.lifecycle.HiltViewModel

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

private const val TAG = "PhotoDetailsViewModel"

/**
 * It's a hilt enabled view model class for PhotoDetailsActivity
 *
 * This view model is responsible to retrieve list of photo from UseCase
 * and provides photo details live result data which can be observed from activity
 *
 * @property getPhotoDetailsUseCase the injected use case
 * this property must be injected
 * @constructor Creates a view model
 */
@HiltViewModel
class PhotoDetailsViewModel @Inject constructor(private val getPhotoDetailsUseCase: GetPhotoDetailsUseCase) :
    ViewModel() {
    private val _photoDetailsList = MutableLiveData<Result<List<PhotoDetails>>>()

    /**
     * Photo details live data which can be observed
     * to get result state and list of photo details
     */
    val photoDetailsList: LiveData<Result<List<PhotoDetails>>>
        get() = _photoDetailsList

    private val disposable = CompositeDisposable()

    init {
        getPhotos()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
        Log.d(TAG, "onCleared()")
    }

    /**
     * Get photo details
     * Emit photo list result to observer
     */
    fun getPhotos() {
        disposable.add(getPhotoDetailsUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _photoDetailsList.value = Result.Loading()
            }
            .subscribe({
                val filteredPhotos = it.distinctBy { element -> element.albumID }
                _photoDetailsList.value = Result.Success(filteredPhotos)
            }) {
                _photoDetailsList.value = Result.Error(ERROR_MESSAGE)
                Log.d(TAG, "${it.message}")
            })
    }
}