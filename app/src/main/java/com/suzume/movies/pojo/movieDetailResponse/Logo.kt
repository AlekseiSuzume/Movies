package com.suzume.movies.pojo.movieDetailResponse


import com.google.gson.annotations.SerializedName

data class Logo(
    @SerializedName("_id")
    val id: String,
    @SerializedName("url")
    val url: String
)