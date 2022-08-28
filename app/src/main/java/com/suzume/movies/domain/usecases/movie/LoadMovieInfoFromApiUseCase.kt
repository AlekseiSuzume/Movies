package com.suzume.movies.domain.usecases.movie

import com.suzume.movies.domain.models.movieDetail.MovieDomainModel
import com.suzume.movies.domain.repository.MovieRepository

class LoadMovieInfoFromApiUseCase(private val repository: MovieRepository) {

    suspend operator fun invoke(id: Int): MovieDomainModel {
        return repository.loadMovieInfoFromApi(id)
    }

}