package com.suzume.movies.presentation.adapter.imageListScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.suzume.movies.data.pojo.imageResponse.Image
import com.suzume.movies.databinding.ImageItemLargeBinding

class ImageListScreenAdapter : ListAdapter<Image, ImageViewHolder>(ImageDiffCallback()) {

    var onReachEndListener: (() -> Unit)? = null
    var onImageClickListener: ((image: Image) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ImageItemLargeBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = getItem(position)
        holder.bind(image)
        if (position == currentList.size - 5) {
            onReachEndListener?.invoke()
        }
        holder.itemView.setOnClickListener {
            onImageClickListener?.invoke(image)
        }
    }

    override fun getItemViewType(position: Int): Int {
        //TODO
        return position
    }

}