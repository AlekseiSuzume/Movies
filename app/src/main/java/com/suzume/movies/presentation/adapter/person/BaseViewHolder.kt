package com.suzume.movies.presentation.adapter.person

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.suzume.movies.pojo.movieDetailResponse.Person

open class BaseViewHolder(binding: ViewBinding):
    RecyclerView.ViewHolder(binding.root) {
     open fun bind(person: Person) {}
}