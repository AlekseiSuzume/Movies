package com.suzume.movies.presentation.movieDetailScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suzume.movies.domain.models.image.ImagesListDomainModel
import com.suzume.movies.domain.models.movieDetail.MovieDomainModel
import com.suzume.movies.domain.models.review.ReviewsListDomainModel
import com.suzume.movies.domain.usecases.image.GetImagesListUseCase
import com.suzume.movies.domain.usecases.movie.AddMovieToDbUseCase
import com.suzume.movies.domain.usecases.movie.GetFavoriteMovieLiveDataUseCase
import com.suzume.movies.domain.usecases.movie.LoadMovieInfoFromApiUseCase
import com.suzume.movies.domain.usecases.movie.RemoveMovieFromDbUseCase
import com.suzume.movies.domain.usecases.review.LoadReviewsListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val addMovieToDbUseCase: AddMovieToDbUseCase,
    private val removeMovieFromDbUseCase: RemoveMovieFromDbUseCase,
    private val loadMovieInfoFromApiUseCase: LoadMovieInfoFromApiUseCase,
    private val getImagesListUseCase: GetImagesListUseCase,
    private val getFavoriteMovieLiveDataUseCase: GetFavoriteMovieLiveDataUseCase,
    private val loadReviewsListUseCase: LoadReviewsListUseCase,
) : ViewModel() {

    private val _movieDtoFromApi = MutableLiveData<MovieDomainModel>()
    val movieDtoFromApi: LiveData<MovieDomainModel> = _movieDtoFromApi

    private val _reviewList = MutableLiveData<ReviewsListDomainModel>()
    val reviewList: LiveData<ReviewsListDomainModel> = _reviewList

    private val _imagesList = MutableLiveData<ImagesListDomainModel>()
    val imagesList: LiveData<ImagesListDomainModel> = _imagesList

    fun getFavoriteMovie(id: Int): LiveData<MovieDomainModel> {
        return getFavoriteMovieLiveDataUseCase(id)
    }

    fun addFavorite(movieDomainModel: MovieDomainModel) {
        viewModelScope.launch {
            addMovieToDbUseCase.invoke(movieDomainModel)
        }
    }

    fun removeFavorite(id: Int) {
        viewModelScope.launch {
            removeMovieFromDbUseCase.invoke(id)
        }
    }

    fun refreshMovieDetailLiveData(id: Int) {
        viewModelScope.launch {
            _movieDtoFromApi.value = loadMovieInfoFromApiUseCase(id)
        }
    }


    fun refreshReviewLiveData(id: Int) {
        viewModelScope.launch {
            _reviewList.value = loadReviewsListUseCase(id, 1)
        }
    }

    fun refreshImageLiveData(movieId: Int) {
        viewModelScope.launch {
            val imagesList = getImagesListUseCase(movieId, 1)
            _imagesList.value = imagesList
        }
    }
}