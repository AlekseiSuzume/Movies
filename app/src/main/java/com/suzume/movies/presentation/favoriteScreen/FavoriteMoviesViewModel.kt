package com.suzume.movies.presentation.favoriteScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suzume.movies.domain.models.movieShort.MovieShortInfoDomainModel
import com.suzume.movies.domain.usecases.movie.GetAllFavoriteMoviesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteMoviesViewModel @Inject constructor(
    private val getAllFavoriteMoviesUseCase: GetAllFavoriteMoviesUseCase
) : ViewModel() {

    private val _allFavoriteMovies = MutableLiveData<List<MovieShortInfoDomainModel>>()
    val allFavoriteMovies: LiveData<List<MovieShortInfoDomainModel>> = _allFavoriteMovies

    fun getAllFavoriteMoviesFromDb2() {
        viewModelScope.launch {
            _allFavoriteMovies.value = getAllFavoriteMoviesUseCase()
        }
    }

}