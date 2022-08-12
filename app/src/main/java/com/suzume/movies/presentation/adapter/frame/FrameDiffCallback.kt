package com.suzume.movies.presentation.adapter.frame

import androidx.recyclerview.widget.DiffUtil
import com.suzume.movies.data.pojo.imageResponse.Image

class FrameDiffCallback : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }
}