package com.suzume.movies.data.network.models.imageResponse


import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("url")
    val imageUrl: String,
)