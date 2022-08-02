package com.suzume.movies.pojo.movieDetailResponse


import com.google.gson.annotations.SerializedName

data class SimilarMovie(
    @SerializedName("alternativeName")
    val alternativeName: String,
    @SerializedName("enName")
    val enName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster")
    val poster: Poster
)