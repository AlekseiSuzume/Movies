package com.suzume.movies.pojo


import com.google.gson.annotations.SerializedName

data class Poster(
    @SerializedName("url")
    val url: String?
)