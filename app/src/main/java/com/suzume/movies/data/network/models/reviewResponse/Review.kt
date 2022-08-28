package com.suzume.movies.data.network.models.reviewResponse


import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("author")
    val author: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("review")
    val review: String,
    @SerializedName("reviewDislikes")
    val reviewDislikes: Int,
    @SerializedName("reviewLikes")
    val reviewLikes: Int,
    @SerializedName("title")
    @ColumnInfo(defaultValue = "")
    val title: String?,
    @SerializedName("type")
    val type: String,
)