package com.suzume.movies.data.pojo.movieDetailResponse


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite_movies")
data class MovieDetail(
    @PrimaryKey
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
    val persons: List<Person>,
    @SerializedName("poster")
    @Embedded
    val poster: Poster,
    @SerializedName("rating")
    @Embedded
    val rating: Rating,
    @SerializedName("type")
    val type: String,
    @SerializedName("typeNumber")
    val typeNumber: Int,
    @SerializedName("videos")
    @Embedded
    val videos: Videos,
    @SerializedName("votes")
    @Embedded
    val votes: Votes,
    @SerializedName("year")
    val year: Int,
//    @SerializedName("similarMovies")
//    @Embedded
//    val SimilarMovies: List<SimilarMovie>,
//    @SerializedName("sequelsAndPrequels")
//    @Embedded
//    val sequelsAndPrequels: List<Movie>,
//    @SerializedName("seasonsInfo")
//    @Embedded
//    val seasonsInfo: List<Season>,
)