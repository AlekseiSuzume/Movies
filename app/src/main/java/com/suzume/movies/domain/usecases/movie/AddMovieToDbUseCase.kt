package com.suzume.movies.domain.usecases.movie

import com.suzume.movies.domain.models.movieDetail.MovieDomainModel
import com.suzume.movies.domain.repository.MovieRepository

class AddMovieToDbUseCase(private val repository: MovieRepository) {

    suspend operator fun invoke(movieDomainModel: MovieDomainModel) {
        repository.addMovieToDb(movieDomainModel)
    }

}