package com.suzume.movies.data.repository.imageRepository

import com.suzume.movies.data.mappers.toImagesDomainModel
import com.suzume.movies.data.network.ApiService
import com.suzume.movies.domain.models.image.ImagesListDomainModel
import com.suzume.movies.domain.repository.ImageRepository
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : ImageRepository {

    override suspend fun getImagesList(movieId: Int, page: Int): ImagesListDomainModel {
        return apiService.loadMovieImage(searchId = movieId, page = page).toImagesDomainModel()
    }
}