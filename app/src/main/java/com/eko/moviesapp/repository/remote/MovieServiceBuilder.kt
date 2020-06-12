package com.eko.moviesapp.repository.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieServiceBuilder {
    fun build(): MovieServices {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/3/discover/")
            .build()
        return retrofit.create(MovieServices::class.java)
    }
}