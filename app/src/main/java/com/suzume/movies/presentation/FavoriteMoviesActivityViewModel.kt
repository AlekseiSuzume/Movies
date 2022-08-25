package com.suzume.movies.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.suzume.movies.App
import com.suzume.movies.data.pojo.movieDetailResponse.MovieDetail
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class FavoriteMoviesActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val db = App.getDatabase().moviesDao()
    private val compositeDisposable = CompositeDisposable()

    private val _allFavoriteMovies = MutableLiveData<List<MovieDetail>>()
    val allFavoriteMovies: LiveData<List<MovieDetail>>
        get() = _allFavoriteMovies

    fun getAllFavoriteMoviesFromDb() {
        val disposable = db.getAllFavoriteMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _allFavoriteMovies.value = it
            }, {
                Log.d("FavoriteActivityViewModel", it.toString())
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}