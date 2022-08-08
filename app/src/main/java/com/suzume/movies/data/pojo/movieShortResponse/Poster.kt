package com.suzume.movies.data.pojo.movieShortResponse


import com.google.gson.annotations.SerializedName

data class Poster(
    @SerializedName("url")
    val url: String
)