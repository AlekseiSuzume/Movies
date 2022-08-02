package com.suzume.movies.pojo.movieDetailResponse


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("name")
    val name: String
)