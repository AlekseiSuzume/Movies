package com.suzume.movies.data.network.models.imageResponse


import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("docs")
    val images: List<ImageDto>,
    @SerializedName("total")
    val total: Int
)