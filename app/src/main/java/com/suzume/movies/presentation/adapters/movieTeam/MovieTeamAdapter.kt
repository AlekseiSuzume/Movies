package com.suzume.movies.presentation.adapters.movieTeam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.suzume.movies.databinding.MovieTeamItemBinding
import com.suzume.movies.databinding.ShowMoreItemTeamBinding
import com.suzume.movies.domain.models.person.PersonDomainModel

class MovieTeamAdapter :
    ListAdapter<PersonDomainModel, RecyclerView.ViewHolder>(MovieTeamDiffCallback()) {

    companion object {
        const val VIEW_TYPE_MOVIE_TEAM = 0
        const val VIEW_TYPE_SHOW_MORE = 1
    }

    var onMovieTeamClickListener: (() -> Unit)? = null
    var onShowMoreClickListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_MOVIE_TEAM -> {
                val binding = MovieTeamItemBinding
                    .inflate(inflater, parent, false)
                MovieTeamViewHolder(binding)
            }
            VIEW_TYPE_SHOW_MORE -> {
                val binding = ShowMoreItemTeamBinding
                    .inflate(inflater, parent, false)
                ShowMoreViewHolder(binding)
            }
            else -> throw RuntimeException("Unknown ViewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val person = getItem(position)
        if (holder is MovieTeamViewHolder) {
            holder.bind(person)
        }
       holder.itemView.setOnClickListener {
           when (holder){
               is MovieTeamViewHolder -> onMovieTeamClickListener?.invoke()
               is ShowMoreViewHolder -> onShowMoreClickListener?.invoke()
           }
       }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position > 5) {
            VIEW_TYPE_SHOW_MORE
        } else {
            VIEW_TYPE_MOVIE_TEAM
        }
    }

    override fun getItemCount(): Int {
        return if (currentList.size > 6) {
            7
        } else {
            currentList.size
        }
    }

}