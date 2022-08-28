package com.suzume.movies.domain.usecases.review

import com.suzume.movies.domain.models.review.ReviewsListDomainModel
import com.suzume.movies.domain.repository.ReviewRepository

class LoadReviewsTypedUseCase(private val repository: ReviewRepository) {

    suspend operator fun invoke(movieId: Int, type: String, page: Int): ReviewsListDomainModel {
        return repository.loadReviewsTypedList(movieId, type, page)
    }

}