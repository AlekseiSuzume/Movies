package com.suzume.movies.data.pojo.imageResponse


import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("docs")
    val images: List<Image>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("total")
    val total: Int
)