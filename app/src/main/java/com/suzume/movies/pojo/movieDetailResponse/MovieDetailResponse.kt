package com.suzume.movies.pojo.movieDetailResponse


import com.google.gson.annotations.SerializedName
import com.suzume.movies.pojo.movieShortResponse.Movie

data class MovieDetailResponse(
    @SerializedName("ageRating")
    val ageRating: Int,
    @SerializedName("alternativeName")
    val alternativeName: String,
    @SerializedName("budget")
    val budget: Budget,
    @SerializedName("countries")
    val countries: List<Country>,
    @SerializedName("createDate")
    val createDate: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("facts")
    val facts: List<Fact>,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: Images,
    @SerializedName("imagesInfo")
    val imagesInfo: ImagesInfo,
    @SerializedName("logo")
    val logo: Logo,
    @SerializedName("movieLength")
    val movieLength: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("persons")
    val persons: List<Person>,
    @SerializedName("poster")
    val poster: Poster,
    @SerializedName("premiere")
    val premiere: Premiere,
    @SerializedName("rating")
    val rating: Rating,
    @SerializedName("ratingMpaa")
    val ratingMpaa: String,
    @SerializedName("seasonsInfo")
    val seasonsInfo: List<Season>,
    @SerializedName("sequelsAndPrequels")
    val sequelsAndPrequels: List<Movie>,
    @SerializedName("shortDescription")
    val shortDescription: String,
    @SerializedName("similarMovies")
    val SimilarMovies: List<SimilarMovie>,
    @SerializedName("slogan")
    val slogan: String,
    @SerializedName("spokenLanguages")
    val spokenLanguages: List<SpokenLanguage>,
    @SerializedName("status")
    val status: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("typeNumber")
    val typeNumber: Int,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("videos")
    val videos: Videos,
    @SerializedName("votes")
    val votes: Votes,
    @SerializedName("year")
    val year: Int
)