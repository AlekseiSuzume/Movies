package com.suzume.movies.presentation.adapter.person

import com.bumptech.glide.Glide
import com.suzume.movies.databinding.PersonItemBinding
import com.suzume.movies.pojo.movieDetailResponse.Person

class PersonViewHolder(var binding: PersonItemBinding) :
    BaseViewHolder(binding) {
    override fun bind(person: Person) {
        Glide.with(binding.root)
            .load(person.photo)
            .into(binding.ivPerson)
        binding.tvPersonName.text = person.name
        binding.tvPersonDescription.text = person.description
    }
}