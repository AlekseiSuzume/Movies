package com.suzume.movies.domain.models.image


data class ImagesListDomainModel(
    val images: List<ImageDomainModel>,
    val totalImages: Int
)