package com.suzume.movies.presentation.adapters.actor

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suzume.movies.databinding.ActorItemBinding
import com.suzume.movies.domain.models.person.PersonDomainModel

class ActorViewHolder(private var binding: ActorItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(personDomainModel: PersonDomainModel) {
        Glide.with(binding.root)
            .load(personDomainModel.photo)
            .into(binding.ivActor)
        binding.tvActorName.text = personDomainModel.name
        binding.tvActorDescription.text = personDomainModel.description
    }
}