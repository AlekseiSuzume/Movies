package com.suzume.movies.data.repository.reviewRepository

import com.suzume.movies.data.mappers.toReviewsListDomainModel
import com.suzume.movies.data.network.ApiFactory
import com.suzume.movies.domain.models.review.ReviewsListDomainModel
import com.suzume.movies.domain.repository.ReviewRepository

class ReviewRepositoryImpl : ReviewRepository {

    private val apiService = ApiFactory.getApiService()


    override suspend fun loadReviewsList(movieId: Int, page: Int): ReviewsListDomainModel {
        return apiService.loadMovieReviewAll(searchId = movieId, page = page).toReviewsListDomainModel()
    }

    override suspend fun loadReviewsTypedList(movieId: Int, type: String, page: Int): ReviewsListDomainModel {
        return apiService.loadMovieReviewTyped(searchId = movieId, searchType = type, page = page)
            .toReviewsListDomainModel()
    }

}