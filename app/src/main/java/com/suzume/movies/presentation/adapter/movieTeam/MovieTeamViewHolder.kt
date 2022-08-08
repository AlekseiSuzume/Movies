package com.suzume.movies.presentation.adapter.movieTeam

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suzume.movies.databinding.MovieTeamItemBinding
import com.suzume.movies.data.pojo.movieDetailResponse.Person


class MovieTeamViewHolder(private val binding: MovieTeamItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(person: Person) {
        with(binding) {
            Glide.with(binding.root)
                .load(person.photo)
                .into(ivPerson)
            tvPersonName.text = person.name
            tvPersonDescription.text = person.enProfession
        }
    }
}