package com.suzume.movies.domain.usecases.movie

import com.suzume.movies.domain.models.movieShort.MovieShortInfoDomainModel
import com.suzume.movies.domain.repository.MovieRepository

class GetAllFavoriteMoviesUseCase(private val repository: MovieRepository) {

    suspend operator fun invoke(): List<MovieShortInfoDomainModel> {
        return repository.getAllFavoriteMovies()
    }

}