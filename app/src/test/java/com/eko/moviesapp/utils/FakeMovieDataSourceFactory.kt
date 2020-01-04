package com.eko.moviesapp.utils

import androidx.paging.DataSource
import com.eko.moviesapp.model.RemoteMovieModel
import com.eko.moviesapp.repository.remote.ApiCallDao

class FakeMovieDataSourceFactory(private val api: ApiCallDao): DataSource.Factory<Int, RemoteMovieModel.Result>() {

    override fun create(): DataSource<Int, RemoteMovieModel.Result> {
        return FakeMovieDataSource(api)
    }

}