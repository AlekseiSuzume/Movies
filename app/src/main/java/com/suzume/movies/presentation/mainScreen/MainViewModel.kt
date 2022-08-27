package com.suzume.movies.presentation.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suzume.movies.data.repository.movieRepository.MovieRepositoryImpl
import com.suzume.movies.domain.models.movieShort.MovieShortInfoDomainModel
import com.suzume.movies.domain.usecases.movie.GetMovieListUseCase
import com.suzume.movies.domain.usecases.movie.SearchMovieUseCase
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    private val movieRepository = MovieRepositoryImpl()
    private val getMovieListUseCase = GetMovieListUseCase(movieRepository)
    private val searchMovieUseCase = SearchMovieUseCase(movieRepository)

    private val _movies = MutableLiveData<List<MovieShortInfoDomainModel>>()
    val movies: LiveData<List<MovieShortInfoDomainModel>> = _movies

    private val _searchMovies = MutableLiveData<List<MovieShortInfoDomainModel>>()
    val searchMovies: LiveData<List<MovieShortInfoDomainModel>> = _searchMovies

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

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
        viewModelScope.launch {
            _isLoading.value = (true)
            val movies = getMovieListUseCase(page)
            _movies.value = _movies.value?.plus(movies) ?: movies
            _isLoading.value = (false)
            page++
        }
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
        viewModelScope.launch {
            val movies = searchMovieUseCase(tempName, page)
            if (page > 1) {
                _searchMovies.value = _searchMovies.value?.plus(movies) ?: movies
                page++
            } else {
                _searchMovies.value = movies
                page++
            }
        }

    }

}