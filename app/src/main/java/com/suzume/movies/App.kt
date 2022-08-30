package com.suzume.movies

import android.app.Application
import android.content.Context
import com.suzume.movies.di.AppComponent
import com.suzume.movies.di.DaggerAppComponent

class App : Application() {

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

    companion object {
        val Context.appComponent: AppComponent
            get() = when (this) {
                is App -> component
                else -> this.applicationContext.appComponent
            }
    }

}

