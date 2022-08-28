package com.suzume.movies.domain.usecases.movie

import com.suzume.movies.domain.repository.MovieRepository

class RemoveMovieFromDbUseCase(private val repository: MovieRepository) {

    suspend operator fun invoke(id: Int){
        repository.removeMovieFromDb(id)
    }

}