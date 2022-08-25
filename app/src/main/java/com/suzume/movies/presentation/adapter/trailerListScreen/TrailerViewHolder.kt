package com.suzume.movies.presentation.adapter.trailerListScreen

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suzume.movies.R
import com.suzume.movies.data.pojo.movieDetailResponse.Trailer
import com.suzume.movies.databinding.TrailerItemLargeBinding

class TrailerViewHolder(val binding: TrailerItemLargeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(trailer: Trailer) {

        binding.tvTrailerNameListScreen.text = trailer.name

        val thumbnail = if (trailer.site == "youtube") {
            val videoId = trailer.url
                .substringAfterLast("=", trailer.url.substringAfterLast("/"))
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