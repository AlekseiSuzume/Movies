package com.suzume.movies.presentation.adapters.trailerListScreen

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suzume.movies.R
import com.suzume.movies.databinding.TrailerItemLargeBinding
import com.suzume.movies.domain.models.movieDetail.TrailerDomainModel

class TrailerViewHolder(val binding: TrailerItemLargeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(trailerDomainModel: TrailerDomainModel) {

        binding.tvTrailerNameListScreen.text = trailerDomainModel.name

        val thumbnail = if (trailerDomainModel.site == "youtube") {
            val videoId = trailerDomainModel.url
                .substringAfterLast("=", trailerDomainModel.url.substringAfterLast("/"))
            "https://img.youtube.com/vi/$videoId/0.jpg"
        } else {
            R.drawable.play_icon
        }
        Glide.with(binding.root)
            .load(thumbnail)
            .placeholder(R.drawable.play_icon)
            .into(binding.ivTrailerPreviewLarge)
    }
}