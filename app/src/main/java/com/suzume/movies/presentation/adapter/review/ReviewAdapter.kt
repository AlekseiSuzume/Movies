package com.suzume.movies.presentation.adapter.review

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.suzume.movies.databinding.ReviewItemBinding
import com.suzume.movies.databinding.ShowMoreItemReviewBinding
import com.suzume.movies.data.pojo.reviewResponse.Review

class ReviewAdapter : ListAdapter<Review, RecyclerView.ViewHolder>(ReviewDiffCallback()) {

    private companion object {
        const val VIEW_TYPE_REVIEW = 0
        const val VIEW_TYPE_SHOW_MORE = 1
    }

    var onReviewClickListener: ((review: Review) -> Unit)? = null
    var onShowMoreClickListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_REVIEW -> {
                val binding = ReviewItemBinding
                    .inflate(inflater, parent, false)
                ReviewViewHolder(binding)
            }
            VIEW_TYPE_SHOW_MORE -> {
                val binding = ShowMoreItemReviewBinding
                    .inflate(inflater, parent, false)
                ShowMoreReviewViewHolder(binding)
            }
            else -> throw RuntimeException("Unknown ViewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val review = getItem(position)
        if (holder is ReviewViewHolder) {
            holder.bind(review)
        }
        holder.itemView.setOnClickListener {
            when (holder.itemViewType) {
                VIEW_TYPE_REVIEW -> onReviewClickListener?.invoke(review)
                VIEW_TYPE_SHOW_MORE -> onShowMoreClickListener?.invoke()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position > 3) {
            VIEW_TYPE_SHOW_MORE
        } else {
            VIEW_TYPE_REVIEW
        }
    }

    override fun getItemCount(): Int {
        return if (currentList.size > 4) {
            5
        } else {
            currentList.size
        }
    }

}