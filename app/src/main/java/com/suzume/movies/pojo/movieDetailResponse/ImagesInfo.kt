package com.suzume.movies.pojo.movieDetailResponse


import com.google.gson.annotations.SerializedName

data class ImagesInfo(
    @SerializedName("framesCount")
    val framesCount: Int,
    @SerializedName("_id")
    val id: String
)