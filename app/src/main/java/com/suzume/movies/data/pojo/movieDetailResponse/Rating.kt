package com.suzume.movies.data.pojo.movieDetailResponse


import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Rating(
//    @SerializedName("await")
//    val await: Double,
//    @SerializedName("filmCritics")
//    val filmCritics: Double,
    @SerializedName("_id")
    @ColumnInfo(name = "rating_id")
    val id: String,
//    @SerializedName("imdb")
//    val imdb: Double,
    @SerializedName("kp")
    @ColumnInfo(name = "rating_kp")
    val kp: Double,
//    @SerializedName("russianFilmCritics")
//    val russianFilmCritics: Double
)