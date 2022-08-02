package com.suzume.movies.presentation.adapter.person

import androidx.recyclerview.widget.DiffUtil
import com.suzume.movies.pojo.movieDetailResponse.Person

class PersonDiffCallback : DiffUtil.ItemCallback<Person>() {

    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }

}