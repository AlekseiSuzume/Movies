package com.suzume.movies.presentation.adapters.personListScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.suzume.movies.databinding.ActorItemLargeBinding
import com.suzume.movies.domain.models.person.PersonDomainModel

class PersonListScreenAdapter : ListAdapter<PersonDomainModel, PersonViewHolder>(PersonDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActorItemLargeBinding.inflate(inflater, parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = getItem(position)
        holder.bind(person)
    }

}