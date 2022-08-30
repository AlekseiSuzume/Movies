package com.suzume.movies.data.repository.movieRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.suzume.movies.data.database.MoviesDao
import com.suzume.movies.data.mappers.toMovieDbModel
import com.suzume.movies.data.mappers.toMovieDomainModel
import com.suzume.movies.data.mappers.toMovieShortInfoDomainModel
import com.suzume.movies.data.network.ApiService
import com.suzume.movies.domain.models.movieDetail.MovieDomainModel
import com.suzume.movies.domain.models.movieShort.MovieShortInfoDomainModel
import com.suzume.movies.domain.repository.MovieRepository
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor (
    private val apiService: ApiService,
    private val db: MoviesDao
        ) : MovieRepository {

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
        db.insertMovie(movieDomainModel.toMovieDbModel())
    }

    override suspend fun removeMovieFromDb(id: Int) {
        db.removeMovie(id)
    }

    override suspend fun getMovieFromDb(id: Int): MovieDomainModel {
        return db.getFavoriteMovie(id).toMovieDomainModel()
    }

    override suspend fun getAllFavoriteMovies(): List<MovieShortInfoDomainModel> {
        return db.getAllFavoriteMovies2().map { it.toMovieShortInfoDomainModel() }
    }

    override fun getFavoriteMovieLiveData(id: Int): LiveData<MovieDomainModel> {
        return Transformations.map(db.getFavoriteMovieLiveData(id)) {
            it?.toMovieDomainModel()
        }
    }

}