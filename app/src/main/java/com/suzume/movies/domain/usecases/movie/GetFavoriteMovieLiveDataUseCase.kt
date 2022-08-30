package com.suzume.movies.domain.usecases.movie

import androidx.lifecycle.LiveData
import com.suzume.movies.domain.models.movieDetail.MovieDomainModel
import com.suzume.movies.domain.repository.MovieRepository
import javax.inject.Inject

class GetFavoriteMovieLiveDataUseCase @Inject constructor(private val repository: MovieRepository) {

    operator fun invoke(id: Int):LiveData<MovieDomainModel> {
        return repository.getFavoriteMovieLiveData(id)
    }

}