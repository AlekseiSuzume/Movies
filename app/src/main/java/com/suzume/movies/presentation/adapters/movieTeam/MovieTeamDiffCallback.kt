package com.suzume.movies.presentation.adapters.movieTeam

import androidx.recyclerview.widget.DiffUtil
import com.suzume.movies.domain.models.person.PersonDomainModel

class MovieTeamDiffCallback : DiffUtil.ItemCallback<PersonDomainModel>() {
    override fun areItemsTheSame(oldItem: PersonDomainModel, newItem: PersonDomainModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PersonDomainModel, newItem: PersonDomainModel): Boolean {
        return oldItem == newItem
    }
}