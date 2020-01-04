package com.eko.moviesapp.pagingRemoteComponent

import androidx.paging.PageKeyedDataSource
import com.eko.moviesapp.BuildConfig
import com.eko.moviesapp.model.RemoteTvShowModel
import com.eko.moviesapp.repository.remote.ApiCallDao
import com.eko.moviesapp.utils.EspressoIdlingResource

class TvShowDataSource(private val api: ApiCallDao): PageKeyedDataSource<Int, RemoteTvShowModel.Result>() {

    companion object{
        const val LANGUAGE = "en-EN"
    }

    private fun fetchData(page: Int): List<RemoteTvShowModel.Result>{
        EspressoIdlingResource.increment()
        val apiCall = api.getTvShowData(BuildConfig.TMDB_API_KEY, LANGUAGE, page)
        val response = apiCall.execute()
        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
            EspressoIdlingResource.decrement();
        }
        return response.body()!!.results
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, RemoteTvShowModel.Result>) {
        callback.onResult(fetchData(1), null, 2)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RemoteTvShowModel.Result>) {
        val page = params.key
        callback.onResult(fetchData(page), page+1)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RemoteTvShowModel.Result>) {}
}