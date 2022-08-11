package com.suzume.movies.presentation.adapter.personListScreen

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suzume.movies.data.pojo.movieDetailResponse.Person
import com.suzume.movies.databinding.ActorItemLargeBinding

class PersonViewHolder(private val binding: ActorItemLargeBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(person: Person) {
        with(binding) {
            Glide.with(binding.root)
                .load(person.photo)
                .into(ivActor)
            tvActorName.text = person.name
            if (person.enProfession != "actor") {
                tvActorDescription.text = person.enProfession
            } else {
                tvActorDescription.text = person.description
            }
        }
    }
}