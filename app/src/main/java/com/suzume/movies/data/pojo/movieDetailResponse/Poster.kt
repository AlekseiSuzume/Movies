package com.suzume.movies.data.pojo.movieDetailResponse


import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Poster(
    @SerializedName("_id")
    @ColumnInfo(name = "Poster_id")
    val id: String,
    @SerializedName("previewUrl")
    val previewUrl: String,
    @SerializedName("url")
    @ColumnInfo(name = "Poster_url")
    val url: String
)