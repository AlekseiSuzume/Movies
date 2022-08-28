package com.suzume.movies.presentation.imageScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suzume.movies.data.repository.imageRepository.ImageRepositoryImpl
import com.suzume.movies.domain.models.image.ImageDomainModel
import com.suzume.movies.domain.usecases.image.GetImagesListUseCase
import kotlinx.coroutines.launch

class ImageListViewModel() : ViewModel() {

    private val imageRepository = ImageRepositoryImpl()
    private val getImagesListUseCase = GetImagesListUseCase(imageRepository)

    private val _imagesList = MutableLiveData<List<ImageDomainModel>>()
    val imageDtoList: LiveData<List<ImageDomainModel>> = _imagesList

    private var page = 1

    fun refreshImageLiveData(movieId: Int) {
        viewModelScope.launch {
            val images = getImagesListUseCase(movieId, page).images
            _imagesList.value = _imagesList.value?.plus(images) ?: images
            page++
        }
    }

}