package com.suzume.movies.data.network.models.movieShortResponse


import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class MovieShortInfoDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster")
    @Embedded
    val poster: Poster,
    @SerializedName("rating")
    @Embedded
    val rating: Rating,
    @SerializedName("year")
    val year: Int
)