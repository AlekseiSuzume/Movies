package com.suzume.movies.presentation.adapters.image

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suzume.movies.R
import com.suzume.movies.databinding.ImageItemBinding
import com.suzume.movies.domain.models.image.ImageDomainModel

class ImageViewHolder(private val binding: ImageItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(imageDomainModel: ImageDomainModel) {
        Glide.with(binding.root)
            .load(imageDomainModel.imageUrl)
            .placeholder(R.drawable.no_poster)
            .into(binding.ivMovieFrame)
           }
    }

