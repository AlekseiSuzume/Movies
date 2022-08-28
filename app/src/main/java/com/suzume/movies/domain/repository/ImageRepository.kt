package com.suzume.movies.domain.repository

import com.suzume.movies.domain.models.image.ImagesListDomainModel

interface ImageRepository {

    suspend fun getImagesList(movieId: Int, page: Int): ImagesListDomainModel

}