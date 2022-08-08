package com.suzume.movies.presentation.adapter.actor

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suzume.movies.databinding.ActorItemBinding
import com.suzume.movies.data.pojo.movieDetailResponse.Person

class ActorViewHolder(private var binding: ActorItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(person: Person) {
        Glide.with(binding.root)
            .load(person.photo)
            .into(binding.ivActor)
        binding.tvActorName.text = person.name
        binding.tvActorDescription.text = person.description
    }
}