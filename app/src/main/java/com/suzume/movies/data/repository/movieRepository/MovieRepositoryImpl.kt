package com.suzume.movies.data.repository.movieRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.suzume.movies.App
import com.suzume.movies.data.mappers.*
import com.suzume.movies.data.network.ApiFactory
import com.suzume.movies.domain.models.movieDetail.MovieDomainModel
import com.suzume.movies.domain.models.movieShort.MovieShortInfoDomainModel
import com.suzume.movies.domain.repository.MovieRepository


class MovieRepositoryImpl() : MovieRepository {

    private val apiService = ApiFactory.getApiService()
    private val db = App.getDatabase()

    override suspend fun getMoviesListUseCase(page: Int): List<MovieShortInfoDomainModel> {
        return apiService.loadMovies(page = page).movieShortInfoDtos.map { it.toMovieShortInfoDomainModel() }
    }

    override suspend fun searchMovieUseCase(
        name: String,
        page: Int,
    ): List<MovieShortInfoDomainModel> {
        return apiService.searchMovie(searchName = name, page = page).movieShortInfoDtos
            .map { it.toMovieShortInfoDomainModel() }
    }

    override suspend fun loadMovieInfoFromApi(id: Int): MovieDomainModel {
        return apiService.loadMovieDetail(searchId = id).toMovieDomainModel()
    }

    override suspend fun addMovieToDb(movieDomainModel: MovieDomainModel) {
        db.moviesDao().insertMovie(movieDomainModel.toMovieDbModel())
    }

    override suspend fun removeMovieFromDb(id: Int) {
        db.moviesDao().removeMovie(id)
    }

    override suspend fun getMovieFromDb(id: Int): MovieDomainModel {
        return db.moviesDao().getFavoriteMovie(id).toMovieDomainModel()
    }

    override suspend fun getAllFavoriteMovies(): List<MovieShortInfoDomainModel> {
        return db.moviesDao().getAllFavoriteMovies2().map { it.toMovieShortInfoDomainModel() }
    }

    override fun getFavoriteMovieLiveData(id: Int): LiveData<MovieDomainModel> {
        return Transformations.map(db.moviesDao().getFavoriteMovieLiveData(id)) {
            it?.toMovieDomainModel()
        }
    }

}