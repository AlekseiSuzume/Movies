package com.suzume.movies.pojo.movieShortResponse


import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("kp")
    val kp: Double,
)