package com.suzume.movies.data.pojo.movieShortResponse


import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("alternativeName")
    val alternativeName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster")
    @Embedded
    val poster: Poster,
    @SerializedName("rating")
    @Embedded
    val rating: Rating,
    @SerializedName("type")
    val type: String,
    @SerializedName("year")
    val year: Int
)