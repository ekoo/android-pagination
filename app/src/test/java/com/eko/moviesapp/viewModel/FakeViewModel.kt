package com.eko.moviesapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.eko.moviesapp.model.LocalMovieModel
import com.eko.moviesapp.model.LocalTvShowModel
import com.eko.moviesapp.model.RemoteMovieModel
import com.eko.moviesapp.model.RemoteTvShowModel
import com.eko.moviesapp.repository.RepositoryImplementation
import kotlinx.coroutines.launch


class FakeViewModel(private val repository: RepositoryImplementation): ViewModel() {

    init {
        //getMovie()
        getTvShowData()
        getFavoriteMovie()
        getFavoriteTvShow()
    }

    val movieData: LiveData<PagedList<RemoteMovieModel.Result>> = repository.fetchMovieData()
    lateinit var tvShowData: LiveData<PagedList<RemoteTvShowModel.Result>>
    lateinit var favoriteMovie: LiveData<PagedList<LocalMovieModel>>
    lateinit var favoriteTvShow: LiveData<PagedList<LocalTvShowModel>>

    fun getFavoriteMovie(){
        favoriteMovie = repository.favoriteMovieData()

    }
    fun getFavoriteTvShow(){
        favoriteTvShow  = repository.favoriteTvShowData()
    }
//    fun getMovie(){
//        movieData = repository.fetchMovieData()
//    }
    fun getTvShowData() {
        tvShowData = repository.fetchTvShowData()
    }
    fun addFavoriteMovie(data: LocalMovieModel) = viewModelScope.launch {
        repository.addFavoriteMovie(data)
    }

    fun deleteFavoriteMovie(data: LocalMovieModel) = viewModelScope.launch {
        repository.deleteFavoriteMovie(data)
    }

    fun addFavoriteTvShow(data: LocalTvShowModel) = viewModelScope.launch {
        repository.addFavoriteTvShow(data)
    }

    fun deleteFavoriteTvShow(data: LocalTvShowModel) = viewModelScope.launch {
        repository.deleteFavoriteTvShow(data)
    }
}