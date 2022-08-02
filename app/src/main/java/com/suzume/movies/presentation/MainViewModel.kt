package com.suzume.movies.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.suzume.movies.api.ApiFactory
import com.suzume.movies.pojo.movieShortResponse.Movie
import com.suzume.movies.pojo.movieShortResponse.MovieResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val apiService = ApiFactory.getApiService()
    private val compositeDisposable = CompositeDisposable()

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var page = 1

    init {
        refreshMoviesLiveData()
    }

    fun refreshMoviesLiveData() {
        val loading = isLoading.value
        if (loading != null && loading) {
            return
        }
        val disposable = loadData(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _isLoading.postValue(true)
            }
            .doAfterTerminate {
                _isLoading.postValue(false)
            }
            .subscribe({
                _movies.value = _movies.value?.plus(it.movies) ?: it.movies
                page++
                Log.d("test", "page $page")
            }, {
                Log.d("MainViewModel:refreshLiveData", it.toString())
            })
        compositeDisposable.add(disposable)
    }

    private fun loadData(page: Int): Single<MovieResponse> {
        return apiService.loadMovies(page = page)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}