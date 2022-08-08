package com.suzume.movies.data.pojo.movieDetailResponse


import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Videos(
    @SerializedName("trailers")
    val trailers: List<Trailer>
)