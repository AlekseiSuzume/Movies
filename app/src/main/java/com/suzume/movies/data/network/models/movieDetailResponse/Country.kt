package com.suzume.movies.data.network.models.movieDetailResponse


import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    val name: String
)