package com.eko.moviesapp.di

import android.app.Application
import com.eko.moviesapp.repository.RepositoryImplementation
import com.eko.moviesapp.repository.local.RoomModule

open class Injection {
    fun injectRepository(application: Application): RepositoryImplementation{
        val movieDao = RoomModule.getInstance(application)!!.movieDao()
        val tvShowDao = RoomModule.getInstance(application)!!.tvShowDao()
        return RepositoryImplementation(movieDao, tvShowDao)
    }

}