package com.suzume.movies.data.pojo.frameResponse


import com.google.gson.annotations.SerializedName

data class Frame(
    @SerializedName("movieId")
    val movieId: Int,
    @SerializedName("previewUrl")
    val previewUrl: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String,
)