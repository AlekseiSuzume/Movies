package com.suzume.movies.domain.repository

import androidx.lifecycle.LiveData
import com.suzume.movies.domain.models.movieDetail.MovieDomainModel
import com.suzume.movies.domain.models.movieShort.MovieShortInfoDomainModel

interface MovieRepository {

    suspend fun getMoviesListUseCase(page: Int): List<MovieShortInfoDomainModel>

    suspend fun searchMovieUseCase(name: String, page: Int): List<MovieShortInfoDomainModel>

    suspend fun loadMovieInfoFromApi(id: Int): MovieDomainModel

    suspend fun addMovieToDb(movieDomainModel: MovieDomainModel)

    suspend fun removeMovieFromDb(id: Int)

    suspend fun getMovieFromDb(id: Int): MovieDomainModel

    suspend fun getAllFavoriteMovies(): List<MovieShortInfoDomainModel>

    fun getFavoriteMovieLiveData(id: Int): LiveData<MovieDomainModel>

}