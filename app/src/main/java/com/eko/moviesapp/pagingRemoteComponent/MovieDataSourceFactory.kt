package com.eko.moviesapp.pagingRemoteComponent

import androidx.paging.DataSource
import com.eko.moviesapp.model.RemoteMovieModel
import com.eko.moviesapp.repository.remote.ApiCallDao

open class MovieDataSourceFactory(private val api: ApiCallDao): DataSource.Factory<Int, RemoteMovieModel.Result>() {

    override fun create(): DataSource<Int, RemoteMovieModel.Result> {
        return MovieDataSource(api)
    }

}