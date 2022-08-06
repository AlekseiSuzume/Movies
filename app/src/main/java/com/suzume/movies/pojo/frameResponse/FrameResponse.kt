package com.suzume.movies.pojo.frameResponse


import com.google.gson.annotations.SerializedName

data class FrameResponse(
    @SerializedName("docs")
    val frames: List<Frame>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("total")
    val total: Int
)