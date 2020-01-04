package com.eko.moviesapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.eko.moviesapp.model.LocalMovieModel
import com.eko.moviesapp.model.LocalTvShowModel
import com.eko.moviesapp.model.RemoteMovieModel
import com.eko.moviesapp.model.RemoteTvShowModel
import com.eko.moviesapp.repository.local.MovieDao
import com.eko.moviesapp.repository.local.TvShowDao
import com.eko.moviesapp.repository.remote.RetrofitModule
import com.eko.moviesapp.utils.FakeMovieDataSourceFactory
import com.eko.moviesapp.utils.FakeTvShowDataSourceFactory
import com.eko.moviesapp.utils.LiveDataTestUtil
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito

class RepositoryImplementationTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val repositoryImplementation = Mockito.mock(RepositoryImplementation::class.java)
    private val apiCall = RetrofitModule
    private val movieDataSourceFactory = FakeMovieDataSourceFactory(apiCall.getInstance())
    private val tvShowDataSourceFactory = FakeTvShowDataSourceFactory(apiCall.getInstance())
    private val movieDao = Mockito.mock(MovieDao::class.java)
    private val tvShowDao = Mockito.mock(TvShowDao::class.java)

    @Test
    fun fetchMovieData() {
        val dummyMovie = LivePagedListBuilder(movieDataSourceFactory, 5).build()
        Mockito.`when`(repositoryImplementation.fetchMovieData()).thenReturn(dummyMovie)
        val observer = Mockito.mock(Observer::class.java)
        repositoryImplementation.fetchMovieData().observeForever(observer as Observer<PagedList<RemoteMovieModel.Result>>)
        Mockito.verify(repositoryImplementation).fetchMovieData()
        val liveDataTest = LiveDataTestUtil.getValue(repositoryImplementation.fetchMovieData())
        assertNotNull(liveDataTest)
    }

    @Test
    fun fetchTvShowData() {
        val dummyTvShow = LivePagedListBuilder(tvShowDataSourceFactory, 5).build()
        Mockito.`when`(repositoryImplementation.fetchTvShowData()).thenReturn(dummyTvShow)
        val observer = Mockito.mock(Observer::class.java) as Observer<PagedList<RemoteTvShowModel.Result>>
        repositoryImplementation.fetchTvShowData().observeForever(observer)
        Mockito.verify(repositoryImplementation).fetchTvShowData()
        val liveDataTest = LiveDataTestUtil.getValue(repositoryImplementation.fetchTvShowData())
        assertNotNull(liveDataTest)
    }

    @Test
    fun getFavoriteMovie(){
        val  dataSourceFactory  = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, LocalMovieModel>
        Mockito.`when`(movieDao.getAll()).thenReturn(dataSourceFactory)
    }

    @Test
    fun getFavoriteTvShow(){
        val  dataSourceFactory  = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, LocalTvShowModel>
        Mockito.`when`(tvShowDao.getAllData()).thenReturn(dataSourceFactory)
    }
}