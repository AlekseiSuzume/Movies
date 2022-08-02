package com.suzume.movies.pojo.movieDetailResponse


import com.google.gson.annotations.SerializedName

data class Fact(
    @SerializedName("spoiler")
    val spoiler: Boolean,
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: String
)