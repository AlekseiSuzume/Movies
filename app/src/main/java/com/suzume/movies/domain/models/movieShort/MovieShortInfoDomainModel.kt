package com.suzume.movies.domain.models.movieShort


data class MovieShortInfoDomainModel(
    val id: Int,
    val name: String,
    val posterUrl: String?,
    val rating: Double,
)