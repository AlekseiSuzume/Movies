package com.suzume.movies.data.network.models.movieShortResponse


import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("docs")
    val movieShortInfoDtos: List<MovieShortInfoDto>,
)