package com.suzume.movies.data.pojo.movieDetailResponse


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Trailer(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("site")
    val site: String,
    @SerializedName("url")
    val url: String,
) : Parcelable