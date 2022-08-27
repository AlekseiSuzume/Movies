package com.suzume.movies.presentation.adapters.trailer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.suzume.movies.databinding.TrailerItemBinding
import com.suzume.movies.databinding.ShowMoreItemTrailerBinding
import com.suzume.movies.domain.models.movieDetail.TrailerDomainModel

class TrailerAdapter : ListAdapter<TrailerDomainModel, RecyclerView.ViewHolder>(TrailerDiffCallback()) {

    private companion object {
        const val VIEW_TYPE_TRAILER = 0
        const val VIEW_TYPE_SHOW_MORE = 1
    }

    var onTrailerItemClickListener: ((trailerDomainModel: TrailerDomainModel) -> Unit)? = null
    var onShowMoreItemClickListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_TRAILER -> {
                val binding = TrailerItemBinding.inflate(inflater, parent, false)
                TrailerViewHolder(binding)
            }
            VIEW_TYPE_SHOW_MORE -> {
                val binding = ShowMoreItemTrailerBinding.inflate(inflater, parent, false)
                ShowMoreTrailerViewHolder(binding)
            }
            else -> throw RuntimeException("Unknown ViewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val trailer = getItem(position)
        if (holder is TrailerViewHolder) {
            holder.bind(trailer)
        }

        holder.itemView.setOnClickListener {
            when (holder.itemViewType) {
                VIEW_TYPE_TRAILER -> onTrailerItemClickListener?.invoke(trailer)
                VIEW_TYPE_SHOW_MORE -> onShowMoreItemClickListener?.invoke()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position > 6) {
            VIEW_TYPE_SHOW_MORE
        } else {
            VIEW_TYPE_TRAILER
        }
    }

    override fun getItemCount(): Int {
        return if (currentList.size > 7) {
            8
        } else {
            currentList.size
        }
    }
}