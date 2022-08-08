package com.suzume.movies.presentation.adapter.trailer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.suzume.movies.databinding.TrailerItemBinding
import com.suzume.movies.data.pojo.movieDetailResponse.Trailer

class TrailerAdapter : ListAdapter<Trailer, RecyclerView.ViewHolder>(TrailerDiffCallback()) {

    private companion object {
        const val VIEW_TYPE_TRAILER = 0
        const val VIEW_TYPE_SHOW_MORE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_TRAILER -> {
                val binding = TrailerItemBinding.inflate(inflater, parent, false)
                TrailerViewHolder(binding)
            }
            VIEW_TYPE_SHOW_MORE -> {
                val binding = TrailerItemBinding.inflate(inflater, parent, false)
                TrailerViewHolder(binding)
            }
            else -> throw RuntimeException("Unknown ViewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val trailer = getItem(position)
        if (holder is TrailerViewHolder) {
            holder.bind(trailer)
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