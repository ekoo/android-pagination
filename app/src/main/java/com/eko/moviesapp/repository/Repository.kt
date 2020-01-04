package com.eko.moviesapp.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.eko.moviesapp.model.LocalMovieModel
import com.eko.moviesapp.model.LocalTvShowModel
import com.eko.moviesapp.model.RemoteMovieModel
import com.eko.moviesapp.model.RemoteTvShowModel

interface Repository {

    //fetch data from api
    fun fetchMovieData(): LiveData<PagedList<RemoteMovieModel.Result>>
    fun fetchTvShowData(): LiveData<PagedList<RemoteTvShowModel.Result>>

    // crud favorite movie
    suspend fun getItemFavoriteMovie(id: String): LocalMovieModel

    suspend fun addFavoriteMovie(data: LocalMovieModel): Long

    suspend fun deleteFavoriteMovie(data: LocalMovieModel): Int

//    crud favorite tv show
    suspend fun getItemFavoriteTvShow(id: String): LocalTvShowModel

    suspend fun addFavoriteTvShow(data: LocalTvShowModel): Long

    suspend fun deleteFavoriteTvShow(data: LocalTvShowModel): Int
}