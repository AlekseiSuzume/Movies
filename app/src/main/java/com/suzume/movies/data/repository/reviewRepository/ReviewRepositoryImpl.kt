package com.suzume.movies.data.repository.reviewRepository

import com.suzume.movies.data.mappers.toReviewsListDomainModel
import com.suzume.movies.data.network.ApiService
import com.suzume.movies.domain.models.review.ReviewsListDomainModel
import com.suzume.movies.domain.repository.ReviewRepository
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : ReviewRepository {

    override suspend fun loadReviewsList(movieId: Int, page: Int): ReviewsListDomainModel {
        return apiService.loadMovieReviewAll(searchId = movieId, page = page)
            .toReviewsListDomainModel()
    }

    override suspend fun loadReviewsTypedList(movieId: Int, type: String, page: Int): ReviewsListDomainModel {
        return apiService.loadMovieReviewTyped(searchId = movieId, searchType = type, page = page)
            .toReviewsListDomainModel()
    }

}