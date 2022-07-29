package com.suzume.movies.pojo


import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("alternativeName")
    val alternativeName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("enName")
    val enName: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster")
    val poster: Poster,
    @SerializedName("rating")
    val rating: Rating,
    @SerializedName("type")
    val type: String,
    @SerializedName("year")
    val year: Int
)