package com.eko.moviesapp.pagingRemoteComponent

import androidx.paging.DataSource
import com.eko.moviesapp.model.RemoteTvShowModel
import com.eko.moviesapp.repository.remote.ApiCallDao

open class TvShowDataSourceFactory(private val api: ApiCallDao): DataSource.Factory<Int, RemoteTvShowModel.Result>() {

    override fun create(): DataSource<Int, RemoteTvShowModel.Result> {
        return TvShowDataSource(api)
    }

}