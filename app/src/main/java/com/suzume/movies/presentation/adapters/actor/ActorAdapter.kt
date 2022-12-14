package com.suzume.movies.presentation.adapters.actor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.suzume.movies.databinding.ActorItemBinding
import com.suzume.movies.databinding.ShowMoreItemActorBinding
import com.suzume.movies.domain.models.person.PersonDomainModel

class ActorAdapter : ListAdapter<PersonDomainModel, RecyclerView.ViewHolder>(ActorDiffCallback()) {

    companion object {
        const val VIEW_TYPE_ACTOR = 0
        const val VIEW_TYPE_SHOW_MORE = 1
    }

    var onActorClickListener: (() -> Unit)? = null
    var onShowMoreClickListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_ACTOR -> {
                val binding = ActorItemBinding
                    .inflate(inflater, parent, false)
                ActorViewHolder(binding)
            }
            VIEW_TYPE_SHOW_MORE -> {
                val binding = ShowMoreItemActorBinding
                    .inflate(inflater, parent, false)
                ShowMoreActorViewHolder(binding)
            }
            else -> throw RuntimeException("Unknown ViewType: $viewType")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val person = getItem(position)
        if (holder is ActorViewHolder) {
            holder.bind(person)
        }

        holder.itemView.setOnClickListener {
            when (holder) {
                is ActorViewHolder -> onActorClickListener?.invoke()
                is ShowMoreActorViewHolder -> onShowMoreClickListener?.invoke()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position > 11) {
            VIEW_TYPE_SHOW_MORE
        } else {
            VIEW_TYPE_ACTOR
        }
    }

    override fun getItemCount(): Int {
        return if (currentList.size > 12) {
            13
        } else {
            currentList.size
        }
    }

}