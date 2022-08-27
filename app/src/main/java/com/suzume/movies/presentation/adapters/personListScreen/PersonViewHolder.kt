package com.suzume.movies.presentation.adapters.personListScreen

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suzume.movies.databinding.ActorItemLargeBinding
import com.suzume.movies.domain.models.person.PersonDomainModel

class PersonViewHolder(private val binding: ActorItemLargeBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(personDomainModel: PersonDomainModel) {
        with(binding) {
            Glide.with(binding.root)
                .load(personDomainModel.photo)
                .into(ivActor)
            tvActorName.text = personDomainModel.name
            if (personDomainModel.enProfession != "actor") {
                tvActorDescription.text = personDomainModel.enProfession
            } else {
                tvActorDescription.text = personDomainModel.description
            }
        }
    }
}