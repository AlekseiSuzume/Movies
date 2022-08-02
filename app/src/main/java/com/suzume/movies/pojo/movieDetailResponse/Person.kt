package com.suzume.movies.pojo.movieDetailResponse


import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("description")
    val description: String,
    @SerializedName("enName")
    val enName: String,
    @SerializedName("enProfession")
    val enProfession: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("photo")
    val photo: String
)