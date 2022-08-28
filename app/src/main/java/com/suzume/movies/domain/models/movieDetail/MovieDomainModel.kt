package com.suzume.movies.domain.models.movieDetail

import com.suzume.movies.domain.models.person.PersonDomainModel


data class MovieDomainModel(
    val id: Int,
    val name: String,
    val alternativeName: String?,
    val countries: String,
    val createDate: String,
    val description: String,
    val genres: String,
    val movieLength: Int,
    val persons: List<PersonDomainModel>,
    val posterUrl: String,
    val rating: Double,
    val trailers: List<TrailerDomainModel>?,
    val votes: Int,
    val year: Int,
)