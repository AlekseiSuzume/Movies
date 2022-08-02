package com.suzume.movies.pojo.movieDetailResponse

import com.google.gson.annotations.SerializedName

data class Season(
    @SerializedName("number")
    val number: Int,
    @SerializedName("episodeCount")
    val episodeCount: Int
)