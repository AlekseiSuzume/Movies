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

class MovieDetailFromDbViewModel(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()
    private val db = App.getDatabase()

    private val _movieDetailFromDb = MutableLiveData<MovieDetail>()
    val movieDetailFromDb: LiveData<MovieDetail>
        get() = _movieDetailFromDb

    fun getLiveDataMovieDetailFromDb(movieId: Int): LiveData<MovieDetail> {
        return db.moviesDao().getFavoriteMovieLiveData(movieId)
    }

    fun getSingleMovieDetailFromDb(movieId: Int) {
        val disposable = db.moviesDao().getFavoriteMovie(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _movieDetailFromDb.value = it
            }, {
                Log.d("MovieDetailFromDbViewModel", it.toString())
            })
        compositeDisposable.add(disposable)
    }

    fun addFavorite(movie: MovieDetail) {
        val disposable = db.moviesDao().insertMovie(movie)
            .subscribeOn(Schedulers.io())
            .subscribe({
            }, {
                Log.d("MovieDetailFromDbViewModel", it.toString())
            })
        compositeDisposable.add(disposable)
    }

    fun removeFavorite(movieId: Int) {
        val disposable = db.moviesDao().removeMovie(movieId)
            .subscribeOn(Schedulers.io())
            .subscribe({
            }, {
                Log.d("MovieDetailFromDbViewModel", it.toString())
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}