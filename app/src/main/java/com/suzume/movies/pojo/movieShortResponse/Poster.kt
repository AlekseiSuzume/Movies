package com.suzume.movies.pojo.movieShortResponse


import com.google.gson.annotations.SerializedName

data class Poster(
    @SerializedName("url")
    val url: String?
)