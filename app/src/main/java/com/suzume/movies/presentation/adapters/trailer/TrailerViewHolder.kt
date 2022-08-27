package com.suzume.movies.presentation.adapters.trailer

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suzume.movies.R
import com.suzume.movies.databinding.TrailerItemBinding
import com.suzume.movies.domain.models.movieDetail.TrailerDomainModel

class TrailerViewHolder(val binding: TrailerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(trailerDomainModel: TrailerDomainModel) {

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
            .into(binding.ivTrailerPreview)

        binding.tvTrailerName.text = trailerDomainModel.name
    }
}