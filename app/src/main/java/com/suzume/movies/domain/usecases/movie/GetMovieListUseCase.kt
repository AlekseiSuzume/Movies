package com.suzume.movies.domain.usecases.movie

import com.suzume.movies.domain.models.movieShort.MovieShortInfoDomainModel
import com.suzume.movies.domain.repository.MovieRepository

class GetMovieListUseCase(private val repository: MovieRepository) {

    suspend operator fun invoke(page: Int): List<MovieShortInfoDomainModel> {
        return repository.getMoviesListUseCase(page)
    }

}