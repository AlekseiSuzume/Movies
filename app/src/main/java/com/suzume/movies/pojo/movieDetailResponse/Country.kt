package com.suzume.movies.pojo.movieDetailResponse


import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    val name: String
)