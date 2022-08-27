package com.suzume.movies.domain.models.movieDetail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrailerDomainModel(
    val id: String,
    val name: String,
    val site: String,
    val url: String,
) : Parcelable