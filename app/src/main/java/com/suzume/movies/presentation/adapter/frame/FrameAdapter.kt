package com.suzume.movies.presentation.adapter.frame

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.suzume.movies.databinding.FrameItemBinding
import com.suzume.movies.databinding.ShowMoreItemFrameBinding
import com.suzume.movies.data.pojo.frameResponse.Frame

class FrameAdapter : ListAdapter<Frame, RecyclerView.ViewHolder>(FrameDiffCallback()) {

    private companion object {
        const val VIEW_TYPE_FRAME = 0
        const val VIEW_TYPE_SHOW_MORE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_FRAME -> {
                val binding = FrameItemBinding
                    .inflate(inflater, parent, false)
                FrameViewHolder(binding)
            }
            VIEW_TYPE_SHOW_MORE -> {
                val binding = ShowMoreItemFrameBinding
                    .inflate(inflater, parent, false)
                ShowMoreFrameHolder(binding)
            }
            else -> throw RuntimeException("Unknown ViewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val frame = getItem(position)
        if (holder is FrameViewHolder) {
            holder.bind(frame)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position > 11) {
            VIEW_TYPE_SHOW_MORE
        } else {
            VIEW_TYPE_FRAME
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