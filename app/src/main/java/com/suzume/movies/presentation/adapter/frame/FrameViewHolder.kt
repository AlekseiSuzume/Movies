package com.suzume.movies.presentation.adapter.frame

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suzume.movies.R
import com.suzume.movies.databinding.FrameItemBinding
import com.suzume.movies.pojo.frameResponse.Frame

class FrameViewHolder(private val binding: FrameItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(frame: Frame) {
        Glide.with(binding.root)
            .load(frame.url)
            .placeholder(R.drawable.no_poster)
            .into(binding.ivMovieFrame)
        Log.d("test", "from Frame holder")
           }
    }


//    fun hide() {
//        with(binding.root) {
//            setBackgroundResource(android.R.color.holo_green_light)
//            visibility = View.GONE
//        }
//    }

