package com.suzume.movies.presentation.adapter.movieTeam

import androidx.recyclerview.widget.DiffUtil
import com.suzume.movies.data.pojo.movieDetailResponse.Person

class MovieTeamDiffCallback : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }
}