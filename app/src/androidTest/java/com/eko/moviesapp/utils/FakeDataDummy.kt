package com.eko.moviesapp.utils

import com.eko.moviesapp.BuildConfig
import com.eko.moviesapp.model.RemoteMovieModel
import com.eko.moviesapp.model.RemoteTvShowModel
import com.eko.moviesapp.repository.remote.ApiCallDao
import com.eko.moviesapp.repository.remote.RetrofitModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val LANGUAGE = "en-EN"
class FakeDataDummy {
    var movieData = ArrayList<RemoteMovieModel>()
    var tvData = ArrayList<RemoteTvShowModel>()

    private val api: ApiCallDao = RetrofitModule.getInstance()

    init {
        generateMoviesData()
        generateTvShowData()
    }

    fun generateMoviesData(): List<RemoteMovieModel> {
        api.getMovieData(BuildConfig.TMDB_API_KEY, LANGUAGE,1).enqueue(object : Callback<RemoteMovieModel> {
            override fun onFailure(call: Call<RemoteMovieModel>, t: Throwable) {
            }

            override fun onResponse(call: Call<RemoteMovieModel>, response: Response<RemoteMovieModel>) {
                if (response.isSuccessful){
                    movieData.add(response.body() as RemoteMovieModel)       //postValue(response.body())
                }
            }
        })
        return movieData
    }

    fun generateTvShowData(): List<RemoteTvShowModel> {
        api.getTvShowData(BuildConfig.TMDB_API_KEY, LANGUAGE, 1).enqueue(object :
            Callback<RemoteTvShowModel> {
            override fun onResponse(call: Call<RemoteTvShowModel>, response: Response<RemoteTvShowModel>) {
                if (response.isSuccessful) {
                    tvData.add(response.body() as RemoteTvShowModel)              //postValue(response.body() as RemoteTvShowModel)
                }
            }
            override fun onFailure(call: Call<RemoteTvShowModel>, t: Throwable) {
            }
        })
        return tvData
    }

}