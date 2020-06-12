package com.eko.moviesapp.repository.remote

import com.eko.moviesapp.model.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieServices {

    @GET("movie?")
    suspend fun getMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language : String,
        @Query("page") page : Int) : ResponseModel
}