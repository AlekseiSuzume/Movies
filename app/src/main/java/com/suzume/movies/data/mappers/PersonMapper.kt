package com.suzume.movies.data.mappers

import com.suzume.movies.data.database.models.PersonDbModel
import com.suzume.movies.data.network.models.movieDetailResponse.PersonDto
import com.suzume.movies.domain.models.person.PersonDomainModel

fun PersonDto.toPersonDomainModel(): PersonDomainModel {
    return PersonDomainModel(
        id = id,
        name = name,
        enName = enName ?: "",
        photo = photo ?: "",
        description = description ?: "",
        enProfession = enProfession ?: ""
    )
}

fun PersonDomainModel.toPersonDbModel(): PersonDbModel {
    return PersonDbModel(
        id = id,
        name = name ?: "",
        enName = enName,
        photo = photo,
        description = description,
        enProfession = enProfession
    )
}

fun PersonDbModel.toPersonDomainModel(): PersonDomainModel {
    return PersonDomainModel(
        id = id,
        name = name,
        enName = enName,
        photo = photo,
        description = description,
        enProfession = enProfession
    )
}