package com.suzume.movies.data.mappers

import com.suzume.movies.data.database.models.MovieDbModel
import com.suzume.movies.data.network.models.movieDetailResponse.MovieDto
import com.suzume.movies.data.network.models.movieShortResponse.MovieShortInfoDto
import com.suzume.movies.domain.models.movieDetail.MovieDomainModel
import com.suzume.movies.domain.models.movieShort.MovieShortInfoDomainModel
import kotlin.math.roundToInt

fun MovieShortInfoDto.toMovieShortInfoDomainModel(): MovieShortInfoDomainModel {
    return MovieShortInfoDomainModel(
        id = id,
        name = name,
        posterUrl = poster.previewUrl,
        rating = (rating.kp * 10).roundToInt() / 10.0
    )
}

fun MovieDto.toMovieDomainModel(): MovieDomainModel {
    return MovieDomainModel(
        id = id,
        name = name,
        alternativeName = alternativeName,
        countries = mutableListOf<String>().apply {
            countries.forEach {
                add(it.name)
            }
        }.joinToString(", "),
        createDate = createDate,
        description = description,
        genres = mutableListOf<String>().apply {
            genres.forEach {
                add(it.name)
            }
        }.joinToString(", "),
        movieLength = movieLength,
        persons = persons.map { it.toPersonDomainModel() },
        posterUrl = poster.url,
        rating = (rating.kp * 10).roundToInt() / 10.0,
        trailers = videos?.trailers?.map { it.toTrailerDomainModel() },
        votes = votes.kp,
        year = year
    )
}

fun MovieDomainModel.toMovieDbModel(): MovieDbModel {
    return MovieDbModel(
        id = id,
        name = name,
        alternativeName = alternativeName,
        countries = countries,
        createDate = createDate,
        description = description,
        genres = genres,
        movieLength = movieLength,
        personDbModels = persons.map { it.toPersonDbModel() },
        posterUrl = posterUrl,
        rating = rating,
        votes = votes,
        year = year
    )
}

fun MovieDbModel.toMovieDomainModel(): MovieDomainModel {
    return MovieDomainModel(
        id = id,
        name = name,
        alternativeName = alternativeName,
        countries = countries,
        createDate = createDate,
        description = description,
        genres = genres,
        movieLength = movieLength,
        persons = personDbModels?.map { it.toPersonDomainModel() } ?: listOf(),
        posterUrl = posterUrl ?: "",
        rating = rating,
        votes = votes ?: 0,
        year = year,
        trailers = null
    )
}

fun MovieDbModel.toMovieShortInfoDomainModel(): MovieShortInfoDomainModel {
    return MovieShortInfoDomainModel(
        id = id,
        name = name,
        posterUrl = posterUrl,
        rating = rating
    )
}