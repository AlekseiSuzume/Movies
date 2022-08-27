package com.suzume.movies.data.mappers

import com.suzume.movies.data.network.models.reviewResponse.ReviewResponse
import com.suzume.movies.domain.models.review.ReviewDomainModel
import com.suzume.movies.domain.models.review.ReviewsListDomainModel

fun ReviewResponse.toReviewsListDomainModel(): ReviewsListDomainModel {
    return ReviewsListDomainModel(
        reviews = mutableListOf<ReviewDomainModel>().apply {
            reviews.forEach {
                add(ReviewDomainModel(
                    id = it.id,
                    author = it.author,
                    date = it.date,
                    review = it.review,
                    reviewDislikes = it.reviewDislikes,
                    reviewLikes = it.reviewLikes,
                    title = it.title,
                    type = it.type
                ))
            }
        },
        totalReviews = total
    )
}