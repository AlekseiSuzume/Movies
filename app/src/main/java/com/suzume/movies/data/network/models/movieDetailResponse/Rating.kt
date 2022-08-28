package com.suzume.movies.data.network.models.movieDetailResponse


import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("_id")
    val id: String,
    @SerializedName("kp")
    val kp: Double,
)