package com.suzume.movies.data.network.models.reviewResponse


import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    @SerializedName("docs")
    val reviews: List<Review>,
    @SerializedName("total")
    val total: Int
)