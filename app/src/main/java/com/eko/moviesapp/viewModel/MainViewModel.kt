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


class MainViewModel(private val repository: RepositoryImplementation): ViewModel() {

    val fetchMovie: LiveData<PagedList<RemoteMovieModel.Result>> = repository.fetchMovieData()
    val fetchTvShow: LiveData<PagedList<RemoteTvShowModel.Result>> = repository.fetchTvShowData()
    val favoriteMovie: LiveData<PagedList<LocalMovieModel>> = repository.favoriteMovieData()
    val favoriteTvShow: LiveData<PagedList<LocalTvShowModel>> = repository.favoriteTvShowData()

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