package com.suzume.movies.pojo.movieDetailResponse


import com.google.gson.annotations.SerializedName

data class Poster(
    @SerializedName("_id")
    val id: String,
    @SerializedName("previewUrl")
    val previewUrl: String,
    @SerializedName("url")
    val url: String
)