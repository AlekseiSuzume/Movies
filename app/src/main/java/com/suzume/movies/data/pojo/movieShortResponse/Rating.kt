package com.suzume.movies.data.pojo.movieShortResponse


import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("kp")
    val kp: Double,
)