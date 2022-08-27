package com.suzume.movies.domain.models.person

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class PersonDomainModel(
    val id: Int,
    val name: String?,
    val enName: String,
    val photo: String,
    val description: String,
    val enProfession: String,
) : Parcelable