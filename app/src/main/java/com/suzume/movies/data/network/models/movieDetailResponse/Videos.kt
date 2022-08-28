package com.suzume.movies.data.network.models.movieDetailResponse


import com.google.gson.annotations.SerializedName

data class Videos(
    @SerializedName("trailers")
    val trailers: List<TrailerDto>?
)