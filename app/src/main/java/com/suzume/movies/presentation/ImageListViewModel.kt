package com.suzume.movies.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.suzume.movies.api.ApiFactory
import com.suzume.movies.data.pojo.imageResponse.Image
import com.suzume.movies.data.pojo.imageResponse.ImageResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ImageListViewModel(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()
    private val apiService = ApiFactory.getApiService()

    private val _imagesList = MutableLiveData<List<Image>>()
    val imageList: LiveData<List<Image>>
        get() = _imagesList

    private var page = 1

    fun refreshImageLiveData(movieId: Int) {
        val disposable = loadImage(movieId, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _imagesList.value = _imagesList.value?.plus(it.images) ?: it.images
                page++
            }, {
                Log.d("ImageListViewModel", it.toString())
            })
        compositeDisposable.add(disposable)
    }

    private fun loadImage(movieId: Int, page: Int): Single<ImageResponse> {
        return apiService.loadMovieImage(searchId = movieId, page = page)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}