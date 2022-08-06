package com.suzume.movies.pojo.reviewResponse


import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("author")
    val author: String,
    @SerializedName("authorId")
    val authorId: Int,
    @SerializedName("date")
    val date: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("movieId")
    val movieId: Int,
    @SerializedName("review")
    val review: String,
    @SerializedName("reviewDislikes")
    val reviewDislikes: Int,
    @SerializedName("reviewLikes")
    val reviewLikes: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
)