package com.suzume.movies.data.network.models.movieShortResponse


import com.google.gson.annotations.SerializedName

data class Poster(
    @SerializedName("previewUrl")
    val previewUrl: String
)