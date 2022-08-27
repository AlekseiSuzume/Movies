package com.suzume.movies.data.mappers

import com.suzume.movies.data.network.models.movieDetailResponse.TrailerDto
import com.suzume.movies.domain.models.movieDetail.TrailerDomainModel

fun TrailerDto.toTrailerDomainModel(): TrailerDomainModel {
    return TrailerDomainModel(
        id = id,
        name = name,
        site = site,
        url = url
    )
}
