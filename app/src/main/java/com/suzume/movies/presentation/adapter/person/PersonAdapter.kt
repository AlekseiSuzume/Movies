package com.suzume.movies.presentation.adapter.person

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.suzume.movies.databinding.PersonItemBinding
import com.suzume.movies.databinding.ShowMoreItemBinding
import com.suzume.movies.pojo.movieDetailResponse.Person

class PersonAdapter : ListAdapter<Person, BaseViewHolder>(PersonDiffCallback()) {

    companion object {
         const val VIEW_TYPE_PERSON = 0
        const val VIEW_TYPE_SHOW_MORE = 1
    }

    var onClickPersonListener: (() -> Unit)? = null
    var onClickShowMoreListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_PERSON -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = PersonItemBinding.inflate(inflater, parent, false)
                PersonViewHolder(binding)
            }
            VIEW_TYPE_SHOW_MORE -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ShowMoreItemBinding.inflate(inflater, parent, false)
                ShowMoreViewHolder(binding)
            }
            else -> throw RuntimeException("Unknown viewType: $viewType")
        }
    }



    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val person = getItem(position)
        holder.bind(person)
        holder.itemView.setOnClickListener {
            when (holder) {
                is PersonViewHolder -> onClickPersonListener?.invoke()
                is ShowMoreViewHolder -> onClickShowMoreListener?.invoke()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position >= 12) {
            VIEW_TYPE_SHOW_MORE
        } else {
            VIEW_TYPE_PERSON
        }
    }

    override fun getItemCount(): Int {
        return if (currentList.size >= 13) {
            13
        } else {
            currentList.size
        }
    }

}