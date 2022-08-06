package com.suzume.movies.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.suzume.movies.api.ApiFactory
import com.suzume.movies.pojo.frameResponse.FrameResponse
import com.suzume.movies.pojo.movieDetailResponse.MovieDetailResponse
import com.suzume.movies.pojo.reviewResponse.Review
import com.suzume.movies.pojo.reviewResponse.ReviewResponse
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

    private val _reviewList = MutableLiveData<ReviewResponse>()
    val reviewList: LiveData<ReviewResponse>
        get() = _reviewList

    private val _frameList = MutableLiveData<FrameResponse>()
    val frameList: LiveData<FrameResponse>
        get() = _frameList

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

    fun refreshReviewListLiveData(id: Int) {
        val disposable = loadReview(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _reviewList.value = it
            }, {
                Log.d("MovieDetailViewModel", it.toString())
            })
        compositeDisposable.add(disposable)
    }

    fun refreshFrameLiveData(id: Int) {
        val disposable = loadFrame(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _frameList.value = it
            }, {
                Log.d("MovieDetailViewModel", it.toString())
            })
        compositeDisposable.add(disposable)
    }

    private fun loadMovieDetail(id: Int): Single<MovieDetailResponse> {
        return apiService.loadMovieDetail(searchId = id)
    }

    private fun loadReview(id: Int): Single<ReviewResponse> {
        return apiService.loadMovieReview(searchId = id)
    }

    private fun loadFrame(id: Int): Single<FrameResponse> {
        return apiService.loadMovieFrame(searchId = id)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}