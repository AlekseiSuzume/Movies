package com.suzume.movies.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.suzume.movies.App
import com.suzume.movies.api.ApiFactory

class PersonListViewModel(application: Application) : AndroidViewModel(application) {
    private val apiService = ApiFactory.getApiService()

    fun personSearch(){

    }
}