package com.suzume.movies.pojo.movieDetailResponse


import com.google.gson.annotations.SerializedName

data class Premiere(
    @SerializedName("bluray")
    val bluray: String,
    @SerializedName("cinema")
    val cinema: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("dvd")
    val dvd: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("russia")
    val russia: String,
    @SerializedName("world")
    val world: String
)