package com.suzume.movies.data.pojo.movieShortResponse


import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("docs")
    val movies: List<Movie>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
)