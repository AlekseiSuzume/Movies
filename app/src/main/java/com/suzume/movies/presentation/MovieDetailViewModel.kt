package com.suzume.movies.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.suzume.movies.api.ApiFactory
import com.suzume.movies.pojo.movieDetailResponse.MovieDetailResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()
    private val apiService = ApiFactory.getApiService()

    private val _movieDetail = MutableLiveData<MovieDetailResponse>()
    val movieDetail: LiveData<MovieDetailResponse>
        get() = _movieDetail


    fun refreshMovieDetailLiveData(id: Int) {
        val disposable = loadMovieDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _movieDetail.value = it
            }, {
                Log.d("MovieDetailViewModel", it.toString())
            })
        compositeDisposable.add(disposable)
    }

    private fun loadMovieDetail(id: Int): Single<MovieDetailResponse> {
        return apiService.loadMovieDetail(searchId = id)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}