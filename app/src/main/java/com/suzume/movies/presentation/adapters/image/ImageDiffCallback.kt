package com.suzume.movies.presentation.adapters.image

import androidx.recyclerview.widget.DiffUtil
import com.suzume.movies.domain.models.image.ImageDomainModel

class ImageDiffCallback : DiffUtil.ItemCallback<ImageDomainModel>() {
    override fun areItemsTheSame(oldItem: ImageDomainModel, newItem: ImageDomainModel): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

    override fun areContentsTheSame(oldItem: ImageDomainModel, newItem: ImageDomainModel): Boolean {
        return oldItem == newItem
    }
}