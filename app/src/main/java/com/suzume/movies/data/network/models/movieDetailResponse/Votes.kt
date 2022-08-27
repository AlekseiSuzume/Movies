package com.suzume.movies.data.network.models.movieDetailResponse


import com.google.gson.annotations.SerializedName

data class Votes(
    @SerializedName("kp")
    val kp: Int,
)