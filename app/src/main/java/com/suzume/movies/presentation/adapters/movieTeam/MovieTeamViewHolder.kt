package com.suzume.movies.presentation.adapters.movieTeam

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suzume.movies.databinding.MovieTeamItemBinding
import com.suzume.movies.domain.models.person.PersonDomainModel


class MovieTeamViewHolder(private val binding: MovieTeamItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(personDomainModel: PersonDomainModel) {
        with(binding) {
            Glide.with(binding.root)
                .load(personDomainModel.photo)
                .into(ivPerson)
            tvPersonName.text = personDomainModel.name
            tvPersonDescription.text = personDomainModel.enProfession
        }
    }
}