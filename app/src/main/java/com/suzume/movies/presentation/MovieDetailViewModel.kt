package com.suzume.movies.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.suzume.movies.App
import com.suzume.movies.api.ApiFactory
import com.suzume.movies.data.pojo.frameResponse.FrameResponse
import com.suzume.movies.data.pojo.movieDetailResponse.MovieDetail
import com.suzume.movies.data.pojo.reviewResponse.ReviewResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()
    private val db = App.getDatabase()
    private val apiService = ApiFactory.getApiService()

    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail: LiveData<MovieDetail>
        get() = _movieDetail

    private val _reviewList = MutableLiveData<ReviewResponse>()
    val reviewList: LiveData<ReviewResponse>
        get() = _reviewList

    private val _frameList = MutableLiveData<FrameResponse>()
    val frameList: LiveData<FrameResponse>
        get() = _frameList

    fun getFavoriteMovie(movieId: Int): LiveData<MovieDetail> {
        return db.moviesDao().getFavoriteMovie(movieId)
    }

    fun addFavorite(movie: MovieDetail) {
        val disposable = db.moviesDao().insertMovie(movie)
            .subscribeOn(Schedulers.io())
            .subscribe({
            }, {
                Log.d("MovieDetailViewModel", it.toString())
            })
        compositeDisposable.add(disposable)
    }

    fun removeFavorite(movieId: Int) {
        val disposable = db.moviesDao().removeMovie(movieId)
            .subscribeOn(Schedulers.io())
            .subscribe({
            }, {
                Log.d("MovieDetailViewModel", it.toString())
            })
        compositeDisposable.add(disposable)
    }

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

    private fun loadMovieDetail(id: Int): Single<MovieDetail> {
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