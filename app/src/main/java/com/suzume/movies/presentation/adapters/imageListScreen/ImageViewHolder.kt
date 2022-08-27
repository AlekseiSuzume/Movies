package com.suzume.movies.presentation.adapters.imageListScreen

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suzume.movies.R
import com.suzume.movies.databinding.ImageItemLargeBinding
import com.suzume.movies.domain.models.image.ImageDomainModel

class ImageViewHolder(private val binding: ImageItemLargeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(imageDomainModel: ImageDomainModel) {
        Glide.with(itemView.context)
            .load(imageDomainModel.imageUrl)
            .placeholder(R.drawable.no_poster)
            .into(binding.imageViewLarge)
    }
}