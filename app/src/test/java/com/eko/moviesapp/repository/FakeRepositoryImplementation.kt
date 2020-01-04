package com.eko.moviesapp.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.eko.moviesapp.model.LocalMovieModel
import com.eko.moviesapp.model.LocalTvShowModel
import com.eko.moviesapp.model.RemoteMovieModel
import com.eko.moviesapp.model.RemoteTvShowModel
import com.eko.moviesapp.pagingRemoteComponent.MovieDataSourceFactory
import com.eko.moviesapp.pagingRemoteComponent.TvShowDataSourceFactory
import com.eko.moviesapp.repository.local.MovieDao
import com.eko.moviesapp.repository.local.TvShowDao
import com.eko.moviesapp.repository.remote.RetrofitModule

class FakeRepositoryImplementation(private val movieDao: MovieDao, private val tvShowDao: TvShowDao) : Repository{

    private val api = RetrofitModule.getInstance()
    private val movieDataSourceFactory = MovieDataSourceFactory(api)
    private val tvShowDataSourceFactory = TvShowDataSourceFactory(api)
    fun favoriteMovieData() = LivePagedListBuilder(movieDao.getAll(), 10).build()
    fun favoriteTvShowData() = LivePagedListBuilder(tvShowDao.getAllData(), 10).build()

    override fun fetchMovieData(): LiveData<PagedList<RemoteMovieModel.Result>> {
        return LivePagedListBuilder(movieDataSourceFactory, 5).build()
    }

    override fun fetchTvShowData(): LiveData<PagedList<RemoteTvShowModel.Result>> {
        return LivePagedListBuilder(tvShowDataSourceFactory, 5).build()
    }


    override suspend fun getItemFavoriteMovie(id: String): LocalMovieModel {
        return movieDao.getItem(id)
    }

    override suspend fun addFavoriteMovie(data: LocalMovieModel): Long {
        return movieDao.insert(data)
    }

    override suspend fun deleteFavoriteMovie(data: LocalMovieModel): Int {
        return movieDao.delete(data)
    }



    override suspend fun getItemFavoriteTvShow(id: String): LocalTvShowModel {
        return tvShowDao.getById(id)
    }

    override suspend fun addFavoriteTvShow(data: LocalTvShowModel): Long {
        return tvShowDao.addData(data)
    }

    override suspend fun deleteFavoriteTvShow(data: LocalTvShowModel): Int {
        return tvShowDao.deleteData(data)
    }

}