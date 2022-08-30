package com.suzume.movies.presentation.movieDetailScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suzume.movies.domain.models.movieDetail.MovieDomainModel
import com.suzume.movies.domain.usecases.movie.AddMovieToDbUseCase
import com.suzume.movies.domain.usecases.movie.GetFavoriteMovieLiveDataUseCase
import com.suzume.movies.domain.usecases.movie.GetMovieFromDbUseCase
import com.suzume.movies.domain.usecases.movie.RemoveMovieFromDbUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailFromDbViewModel @Inject constructor(
    private val addMovieToDbUseCase: AddMovieToDbUseCase,
    private val removeMovieFromDbUseCse: RemoveMovieFromDbUseCase,
    private val getMovieFromDbUseCase: GetMovieFromDbUseCase,
    private val getFavoriteMovieLiveDataUseCase: GetFavoriteMovieLiveDataUseCase
) : ViewModel() {

    private val _movieFromDb = MutableLiveData<MovieDomainModel>()
    val movieFromDb: LiveData<MovieDomainModel> = _movieFromDb

    fun getLiveDataMovieDetailFromDb(id: Int): LiveData<MovieDomainModel> {
        return getFavoriteMovieLiveDataUseCase(id)
    }

    fun getMovieFromDb(id: Int) {
        viewModelScope.launch {
            _movieFromDb.value = getMovieFromDbUseCase(id)
        }
    }

    fun addFavorite(movieDomainModel: MovieDomainModel) {
        viewModelScope.launch {
            addMovieToDbUseCase.invoke(movieDomainModel)
        }
    }

    fun removeFavorite(id: Int) {
        viewModelScope.launch {
            removeMovieFromDbUseCse.invoke(id)
        }
    }

}