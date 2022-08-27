package com.suzume.movies.data.network.models.movieShortResponse


import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("kp")
    val kp: Double,
)