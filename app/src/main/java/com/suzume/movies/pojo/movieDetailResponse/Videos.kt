package com.suzume.movies.pojo.movieDetailResponse


import com.google.gson.annotations.SerializedName

data class Videos(
    @SerializedName("_id")
    val id: String,
    @SerializedName("teasers")
    val teasers: List<Any>,
    @SerializedName("trailers")
    val trailers: List<Trailer>
)