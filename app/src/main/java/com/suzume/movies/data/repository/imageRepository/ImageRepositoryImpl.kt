package com.suzume.movies.data.repository.imageRepository

import com.suzume.movies.data.mappers.toImagesDomainModel
import com.suzume.movies.data.network.ApiFactory
import com.suzume.movies.domain.models.image.ImagesListDomainModel
import com.suzume.movies.domain.repository.ImageRepository

class ImageRepositoryImpl:ImageRepository {

    private val apiService = ApiFactory.getApiService()

    override suspend fun getImagesList(movieId: Int, page: Int): ImagesListDomainModel {
        return apiService.loadMovieImage(searchId = movieId, page = page).toImagesDomainModel()
    }
}