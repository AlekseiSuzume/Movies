package com.suzume.movies.domain.usecases.image

import com.suzume.movies.domain.models.image.ImagesListDomainModel
import com.suzume.movies.domain.repository.ImageRepository
import javax.inject.Inject

class GetImagesListUseCase @Inject constructor(private val repository: ImageRepository) {

    suspend operator fun invoke(movieId: Int, page: Int): ImagesListDomainModel {
        return repository.getImagesList(movieId, page)
    }

}