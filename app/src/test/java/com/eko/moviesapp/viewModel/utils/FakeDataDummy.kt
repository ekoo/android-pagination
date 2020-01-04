package com.eko.moviesapp.viewModel.utils

import com.eko.moviesapp.BuildConfig
import com.eko.moviesapp.model.RemoteMovieModel
import com.eko.moviesapp.model.RemoteTvShowModel
import com.eko.moviesapp.repository.remote.ApiCallDao
import com.eko.moviesapp.repository.remote.RetrofitModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FakeDataDummy {

    companion object{
        private const val LANGUAGE = "en-EN"
    }

    var movieData = ArrayList<RemoteMovieModel.Result>()
    var tvData =  ArrayList<RemoteTvShowModel.Result>()

    private val api: ApiCallDao = RetrofitModule.getInstance()

    fun generateMoviesData(): List<RemoteMovieModel.Result> {
        api.getMovieData(BuildConfig.TMDB_API_KEY, LANGUAGE, 1).enqueue(object : Callback<RemoteMovieModel> {
            override fun onFailure(call: Call<RemoteMovieModel>, t: Throwable) {
            }

            override fun onResponse(call: Call<RemoteMovieModel>, response: Response<RemoteMovieModel>) {
                if (response.isSuccessful){
                    movieData.addAll(response.body()!!.results)
                }
            }
        })
        return movieData
    }

    fun generateTvShowData(): List<RemoteTvShowModel.Result> {
        api.getTvShowData(BuildConfig.TMDB_API_KEY, LANGUAGE, 1).enqueue(object : Callback<RemoteTvShowModel> {
            override fun onResponse(call: Call<RemoteTvShowModel>, response: Response<RemoteTvShowModel>) {
                if (response.isSuccessful) {
                    tvData.addAll(response.body()!!.results)
                }
            }
            override fun onFailure(call: Call<RemoteTvShowModel>, t: Throwable) {
            }
        })
        return tvData
    }
}