package com.suzume.movies.domain.usecases.movie

import com.suzume.movies.domain.repository.MovieRepository
import javax.inject.Inject

class RemoveMovieFromDbUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(id: Int){
        repository.removeMovieFromDb(id)
    }

}