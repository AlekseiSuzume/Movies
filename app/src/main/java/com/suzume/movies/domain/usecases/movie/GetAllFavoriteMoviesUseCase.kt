package com.suzume.movies.domain.usecases.movie

import com.suzume.movies.domain.models.movieShort.MovieShortInfoDomainModel
import com.suzume.movies.domain.repository.MovieRepository
import javax.inject.Inject

class GetAllFavoriteMoviesUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(): List<MovieShortInfoDomainModel> {
        return repository.getAllFavoriteMovies()
    }

}