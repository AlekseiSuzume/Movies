package com.suzume.movies.data.mappers

import com.suzume.movies.data.network.models.imageResponse.ImageResponse
import com.suzume.movies.domain.models.image.ImageDomainModel
import com.suzume.movies.domain.models.image.ImagesListDomainModel

fun ImageResponse.toImagesDomainModel(): ImagesListDomainModel {
    return ImagesListDomainModel(
        images = mutableListOf<ImageDomainModel>().apply {
            images.forEach {
                add(ImageDomainModel(it.imageUrl))
            }
        },
        totalImages = total
    )
}