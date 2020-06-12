package com.eko.moviesapp.dataSource


import androidx.paging.PagingSource
import com.eko.moviesapp.model.ResponseModel
import com.eko.moviesapp.repository.remote.MovieServiceBuilder
import com.eko.moviesapp.repository.remote.MovieServices

class MoviePagingSource(private val service: MovieServices = MovieServiceBuilder.build()) : PagingSource<Int, ResponseModel.MovieModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseModel.MovieModel> {

        val pagePosition = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = service.getMovie(API_KET, LANGUAGE, pagePosition).movieModels
            LoadResult.Page(data = response, prevKey = if (pagePosition == STARTING_PAGE_INDEX) null else pagePosition - 1, nextKey = if (response.isEmpty()) null else pagePosition + 1)
        } catch (exception: Exception){
            LoadResult.Error(exception)
        }

    }

    companion object{
        private const val STARTING_PAGE_INDEX = 1
        private const val API_KET = "f40dd80dfd15e64b2484c096e983d840"
        private const val LANGUAGE = "en-EN"
    }
}