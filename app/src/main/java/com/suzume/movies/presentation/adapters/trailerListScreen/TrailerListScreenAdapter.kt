package com.suzume.movies.presentation.adapters.trailerListScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.suzume.movies.databinding.TrailerItemLargeBinding
import com.suzume.movies.domain.models.movieDetail.TrailerDomainModel

class TrailerListScreenAdapter : ListAdapter<TrailerDomainModel, TrailerViewHolder>(TrailerDiffCallback()) {

    var onTrailerClickListener: ((trailerDomainModel: TrailerDomainModel) -> Unit)? = null

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

}