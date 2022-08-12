package com.suzume.movies.presentation.adapter.trailerListScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.suzume.movies.data.pojo.movieDetailResponse.Trailer
import com.suzume.movies.databinding.TrailerItemLargeBinding

class TrailerListScreenAdapter : ListAdapter<Trailer, TrailerViewHolder>(TrailerDiffCallback()) {

    var onTrailerClickListener: ((trailer: Trailer) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TrailerItemLargeBinding.inflate(inflater, parent, false)
        return TrailerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        val trailer = getItem(position)
        holder.bind(trailer)
        holder.itemView.setOnClickListener {
            onTrailerClickListener?.invoke(trailer)
        }
    }

    override fun onViewRecycled(holder: TrailerViewHolder) {
        super.onViewRecycled(holder)
    }
}