package com.suzume.movies.pojo.movieDetailResponse


import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("await")
    val await: Double,
    @SerializedName("filmCritics")
    val filmCritics: Double,
    @SerializedName("_id")
    val id: String,
    @SerializedName("imdb")
    val imdb: Double,
    @SerializedName("kp")
    val kp: Double,
    @SerializedName("russianFilmCritics")
    val russianFilmCritics: Double
)