package com.suzume.movies.presentation.adapters.trailer

import androidx.recyclerview.widget.DiffUtil
import com.suzume.movies.domain.models.movieDetail.TrailerDomainModel

class TrailerDiffCallback: DiffUtil.ItemCallback<TrailerDomainModel>() {
    override fun areItemsTheSame(oldItem: TrailerDomainModel, newItem: TrailerDomainModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TrailerDomainModel, newItem: TrailerDomainModel): Boolean {
        return oldItem == newItem
    }
}