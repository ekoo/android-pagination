package com.eko.moviesapp.repository.remote

import com.eko.moviesapp.model.RemoteMovieModel
import com.eko.moviesapp.model.RemoteTvShowModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCallDao {
    @GET("movie?")
    fun getMovieData(@Query("api_key") apiKey: String, @Query("language") language : String, @Query("page") page : Int) : Call<RemoteMovieModel>

    @GET("tv?")
    fun getTvShowData(@Query("api_key") apiKey: String, @Query("language") language: String, @Query("page") page : Int) : Call<RemoteTvShowModel>
}