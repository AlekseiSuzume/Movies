package com.suzume.movies.domain.repository

import com.suzume.movies.domain.models.review.ReviewsListDomainModel

interface ReviewRepository {

    suspend fun loadReviewsList(movieId: Int, page: Int): ReviewsListDomainModel

    suspend fun loadReviewsTypedList(movieId: Int, type: String, page: Int): ReviewsListDomainModel

}