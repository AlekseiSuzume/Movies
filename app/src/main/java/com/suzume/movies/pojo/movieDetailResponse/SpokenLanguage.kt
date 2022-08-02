package com.suzume.movies.pojo.movieDetailResponse


import com.google.gson.annotations.SerializedName

data class SpokenLanguage(
    @SerializedName("name")
    val name: String,
    @SerializedName("nameEn")
    val nameEn: String
)