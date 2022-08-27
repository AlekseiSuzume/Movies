package com.suzume.movies.presentation.movieDetailScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suzume.movies.data.repository.movieRepository.MovieRepositoryImpl
import com.suzume.movies.domain.models.movieDetail.MovieDomainModel
import com.suzume.movies.domain.usecases.movie.AddMovieToDbUseCase
import com.suzume.movies.domain.usecases.movie.GetFavoriteMovieLiveDataUseCase
import com.suzume.movies.domain.usecases.movie.GetMovieFromDbUseCase
import com.suzume.movies.domain.usecases.movie.RemoveMovieFromDbUseCase
import kotlinx.coroutines.launch

class MovieDetailFromDbViewModel() : ViewModel() {

    private val movieRepository = MovieRepositoryImpl()
    private val addMovieToDb = AddMovieToDbUseCase(movieRepository)
    private val removeMovieFromDb = RemoveMovieFromDbUseCase(movieRepository)
    private val getMovieFromDbUseCase = GetMovieFromDbUseCase(movieRepository)
    private val getFavoriteMovieLiveDataUseCase = GetFavoriteMovieLiveDataUseCase(movieRepository)

    private val _movieFromDb = MutableLiveData<MovieDomainModel>()
    val movieFromDb: LiveData<MovieDomainModel> = _movieFromDb

    fun getLiveDataMovieDetailFromDb(id: Int) : LiveData<MovieDomainModel> {
           return getFavoriteMovieLiveDataUseCase(id)
    }

    fun getMovieFromDb(id: Int) {
        viewModelScope.launch {
            _movieFromDb.value = getMovieFromDbUseCase(id)
        }
    }

    fun addFavorite(movieDomainModel: MovieDomainModel) {
        viewModelScope.launch {
            addMovieToDb.invoke(movieDomainModel)
        }
    }

    fun removeFavorite(id: Int) {
        viewModelScope.launch {
            removeMovieFromDb.invoke(id)
        }
    }

}