package com.suzume.movies.data.network.models.movieDetailResponse


import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("ageRating")
    val ageRating: Int,
    @SerializedName("alternativeName")
    val alternativeName: String?,
    @SerializedName("countries")
    val countries: List<Country>,
    @SerializedName("createDate")
    val createDate: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("movieLength")
    val movieLength: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("persons")
    val persons: List<PersonDto>,
    @SerializedName("poster")
    val poster: Poster,
    @SerializedName("rating")
    val rating: Rating,
    @SerializedName("type")
    val type: String,
    @SerializedName("typeNumber")
    val typeNumber: Int,
    @SerializedName("videos")
    val videos: Videos?,
    @SerializedName("votes")
    val votes: Votes,
    @SerializedName("year")
    val year: Int,
)