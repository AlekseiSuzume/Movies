package com.suzume.movies.presentation.adapters.image

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.suzume.movies.databinding.ImageItemBinding
import com.suzume.movies.databinding.ShowMoreItemFrameBinding
import com.suzume.movies.domain.models.image.ImageDomainModel

class ImageAdapter : ListAdapter<ImageDomainModel, RecyclerView.ViewHolder>(ImageDiffCallback()) {

    private companion object {
        const val VIEW_TYPE_IMAGE = 0
        const val VIEW_TYPE_SHOW_MORE = 1
    }

    var onImageClickListener: ((imageDomainModel: ImageDomainModel) -> Unit)? = null
    var onShowMoreClickListener: (() -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_IMAGE -> {
                val binding = ImageItemBinding
                    .inflate(inflater, parent, false)
                ImageViewHolder(binding)
            }
            VIEW_TYPE_SHOW_MORE -> {
                val binding = ShowMoreItemFrameBinding
                    .inflate(inflater, parent, false)
                ShowMoreImageHolder(binding)
            }
            else -> throw RuntimeException("Unknown ViewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val image = getItem(position)
        if (holder is ImageViewHolder) {
            holder.bind(image)
        }
        holder.itemView.setOnClickListener {
            when (holder.itemViewType) {
                VIEW_TYPE_IMAGE -> onImageClickListener?.invoke(image)
                VIEW_TYPE_SHOW_MORE -> onShowMoreClickListener?.invoke()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position > 11) {
            VIEW_TYPE_SHOW_MORE
        } else {
            VIEW_TYPE_IMAGE
        }
    }

    override fun getItemCount(): Int {
        return if (currentList.size > 12) {
            13
        } else {
            currentList.size
        }
    }

}