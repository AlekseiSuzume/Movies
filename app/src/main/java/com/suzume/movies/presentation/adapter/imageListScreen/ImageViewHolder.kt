package com.suzume.movies.presentation.adapter.imageListScreen

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suzume.movies.R
import com.suzume.movies.data.pojo.imageResponse.Image
import com.suzume.movies.databinding.ImageItemBinding

class ImageViewHolder(private val binding: ImageItemBinding):
RecyclerView.ViewHolder(binding.root){

    fun bind(image: Image){
        Glide.with(itemView.context)
            .load(image.url)
            .placeholder(R.drawable.no_poster)
            .into(binding.imageView)
    }
}