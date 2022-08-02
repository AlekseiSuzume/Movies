package com.suzume.movies.pojo.movieDetailResponse


import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("backdropsCount")
    val backdropsCount: Int,
    @SerializedName("framesCount")
    val framesCount: Int,
    @SerializedName("postersCount")
    val postersCount: Int
)