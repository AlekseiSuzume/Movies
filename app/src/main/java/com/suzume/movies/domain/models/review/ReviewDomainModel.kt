package com.suzume.movies.domain.models.review

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewDomainModel(
    val id: Int,
    val author: String,
    val date: String,
    val review: String,
    val reviewDislikes: Int,
    val reviewLikes: Int,
    val title: String?,
    val type: String,
) : Parcelable