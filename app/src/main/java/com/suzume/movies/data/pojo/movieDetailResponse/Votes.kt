package com.suzume.movies.data.pojo.movieDetailResponse


import com.google.gson.annotations.SerializedName

data class Votes(
    @SerializedName("kp")
    val kp: Int,
)