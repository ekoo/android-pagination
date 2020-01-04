package com.eko.moviesapp.pagingRemoteComponent

import androidx.paging.PageKeyedDataSource
import com.eko.moviesapp.BuildConfig
import com.eko.moviesapp.model.RemoteMovieModel
import com.eko.moviesapp.repository.remote.ApiCallDao
import com.eko.moviesapp.utils.EspressoIdlingResource

class MovieDataSource(private val api: ApiCallDao): PageKeyedDataSource<Int, RemoteMovieModel.Result>() {

    companion object{
        const val LANGUAGE = "en-EN"
    }

    private fun fetchData(page: Int): List<RemoteMovieModel.Result>{
        EspressoIdlingResource.increment()
        val apiCall = api.getMovieData(BuildConfig.TMDB_API_KEY, LANGUAGE, page)
        val response = apiCall.execute()
        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
            EspressoIdlingResource.decrement();
        }
        return response.body()!!.results

    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, RemoteMovieModel.Result>) {
        callback.onResult(fetchData(1), null, 2)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RemoteMovieModel.Result>) {
        val page = params.key
        callback.onResult(fetchData(page), page+1)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RemoteMovieModel.Result>) {}
}