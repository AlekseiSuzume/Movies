package com.suzume.movies.presentation.adapters.imageListScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.suzume.movies.databinding.ImageItemLargeBinding
import com.suzume.movies.domain.models.image.ImageDomainModel

class ImageListScreenAdapter : ListAdapter<ImageDomainModel, ImageViewHolder>(ImageDiffCallback()) {

    var onReachEndListener: (() -> Unit)? = null
    var onImageClickListener: ((imageDomainModel: ImageDomainModel) -> Unit)? = null

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

}