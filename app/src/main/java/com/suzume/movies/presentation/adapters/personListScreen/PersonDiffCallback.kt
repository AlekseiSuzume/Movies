package com.suzume.movies.presentation.adapters.personListScreen

import androidx.recyclerview.widget.DiffUtil
import com.suzume.movies.domain.models.person.PersonDomainModel

class PersonDiffCallback : DiffUtil.ItemCallback<PersonDomainModel>() {
    override fun areItemsTheSame(oldItem: PersonDomainModel, newItem: PersonDomainModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PersonDomainModel, newItem: PersonDomainModel): Boolean {
        return oldItem == newItem
    }
}