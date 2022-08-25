package com.suzume.movies.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.suzume.movies.api.ApiFactory
import com.suzume.movies.data.pojo.movieShortResponse.Movie
import com.suzume.movies.data.pojo.movieShortResponse.MovieResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val apiService = ApiFactory.getApiService()
    private val compositeDisposable = CompositeDisposable()

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _searchMovies = MutableLiveData<List<Movie>>()
    val searchMovies: LiveData<List<Movie>>
        get() = _searchMovies

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var page = 1
    private var tempName = ""

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
            }, {
                Log.d("MainViewModel", it.toString())
            })
        compositeDisposable.add(disposable)
    }

    fun refreshSearchMovieLiveData(name: String) {
        val loading = isLoading.value
        if (loading != null && loading) {
            return
        }
        if (tempName != name) {
            page = 1
            tempName = name
        }
        val disposable = loadSearchData(tempName, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _isLoading.postValue(true)
            }
            .doAfterTerminate {
                _isLoading.postValue(false)
            }
            .subscribe({
                if (page > 1) {
                    _searchMovies.value = _searchMovies.value?.plus(it.movies) ?: it.movies
                    page++
                } else {
                    _searchMovies.value = it.movies
                    page++
                }
            }, {
                Log.d("MainViewModel", it.toString())
            })
        compositeDisposable.add(disposable)
    }

    private fun loadData(page: Int): Single<MovieResponse> {
        return apiService.loadMovies(page = page)
    }

    private fun loadSearchData(name: String, page: Int): Single<MovieResponse> {
        return apiService.searchMovie(searchName = name, page = page)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}