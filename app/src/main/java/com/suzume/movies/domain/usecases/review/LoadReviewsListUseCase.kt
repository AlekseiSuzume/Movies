package com.suzume.movies.domain.usecases.review

import com.suzume.movies.domain.models.review.ReviewsListDomainModel
import com.suzume.movies.domain.repository.ReviewRepository

class LoadReviewsListUseCase(private val repository: ReviewRepository) {

    suspend operator fun invoke(movieId: Int, page: Int): ReviewsListDomainModel {
        return repository.loadReviewsList(movieId, page)
    }

}