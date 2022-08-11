package com.suzume.movies.data.pojo.movieDetailResponse


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    @SerializedName("description")
    val description: String?,
    @SerializedName("enName")
    val enName: String?,
    @SerializedName("enProfession")
    val enProfession: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("photo")
    val photo: String?
) : Parcelable