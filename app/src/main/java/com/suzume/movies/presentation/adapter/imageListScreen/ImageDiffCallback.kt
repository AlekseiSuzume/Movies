package com.suzume.movies.presentation.adapter.imageListScreen

import androidx.recyclerview.widget.DiffUtil
import com.suzume.movies.data.pojo.imageResponse.Image

class ImageDiffCallback : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }
}