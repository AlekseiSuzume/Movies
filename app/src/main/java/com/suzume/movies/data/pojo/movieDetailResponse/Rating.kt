package com.suzume.movies.data.pojo.movieDetailResponse


import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("_id")
    @ColumnInfo(name = "rating_id")
    val id: String,
    @SerializedName("kp")
    @ColumnInfo(name = "rating_kp")
    val kp: Double,
)