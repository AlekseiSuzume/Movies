package com.suzume.movies.pojo.movieDetailResponse


import com.google.gson.annotations.SerializedName

data class Budget(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("value")
    val value: Int
)