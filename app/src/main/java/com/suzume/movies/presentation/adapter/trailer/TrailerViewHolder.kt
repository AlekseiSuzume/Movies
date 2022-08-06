package com.suzume.movies.presentation.adapter.trailer

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suzume.movies.R
import com.suzume.movies.databinding.TrailerItemBinding
import com.suzume.movies.pojo.movieDetailResponse.Trailer

class TrailerViewHolder(val binding: TrailerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(trailer: Trailer) {

        val thumbnail = if (trailer.site == "youtube") {
            val videoId = trailer.url
                .substringAfterLast("=", trailer.url.substringAfterLast("/"))
            Log.d("test", "videoId $videoId")
            "https://img.youtube.com/vi/$videoId/0.jpg"
        } else {
            R.drawable.play_icon
        }

        Glide.with(binding.root)
            .load(thumbnail)
            .placeholder(R.drawable.play_icon)
            .into(binding.ivTrailerPreview)

        binding.tvTrailerName.text = trailer.name
    }
}