package com.suzume.movies.data.pojo.movieDetailResponse


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("name")
    val name: String
)