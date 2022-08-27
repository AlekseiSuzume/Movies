package com.suzume.movies.data.network.models.movieDetailResponse


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("name")
    val name: String
)