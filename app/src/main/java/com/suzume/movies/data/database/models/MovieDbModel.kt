package com.suzume.movies.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class MovieDbModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val alternativeName: String?,
    val countries: String,
    val createDate: String,
    val description: String,
    val genres: String,
    val movieLength: Int,
    val personDbModels: List<PersonDbModel>?,
    val posterUrl: String?,
    val rating: Double,
    val votes: Int?,
    val year: Int,
)