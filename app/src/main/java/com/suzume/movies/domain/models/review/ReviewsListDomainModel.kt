package com.suzume.movies.domain.models.review


data class ReviewsListDomainModel(
    val reviews: List<ReviewDomainModel>,
    val totalReviews: Int
)