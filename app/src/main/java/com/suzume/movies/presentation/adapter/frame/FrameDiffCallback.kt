package com.suzume.movies.presentation.adapter.frame

import androidx.recyclerview.widget.DiffUtil
import com.suzume.movies.data.pojo.frameResponse.Frame

class FrameDiffCallback : DiffUtil.ItemCallback<Frame>() {
    override fun areItemsTheSame(oldItem: Frame, newItem: Frame): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Frame, newItem: Frame): Boolean {
        return oldItem == newItem
    }
}