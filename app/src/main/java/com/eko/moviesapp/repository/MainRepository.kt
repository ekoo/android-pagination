package com.eko.moviesapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.eko.moviesapp.dataSource.MoviePagingSource
import com.eko.moviesapp.model.ResponseModel
import kotlinx.coroutines.flow.Flow

class MainRepository {

    fun getMovie(): Flow<PagingData<ResponseModel.MovieModel>> {
        return Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = { MoviePagingSource() }).flow
    }

}