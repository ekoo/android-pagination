package com.eko.moviesapp.viewModel

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.eko.moviesapp.model.LocalMovieModel
import com.eko.moviesapp.model.RemoteMovieModel
import com.eko.moviesapp.model.RemoteTvShowModel
import com.eko.moviesapp.repository.RepositoryImplementation
import com.eko.moviesapp.utils.LiveDataTestUtil
import com.eko.moviesapp.viewModel.utils.FakeDataDummy
import com.eko.moviesapp.viewModel.utils.mockPagedList
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.mockito.Mock
import org.junit.Assert.assertNotNull
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@Suppress("UNCHECKED_CAST")
class MainViewModelTest {
    @Mock
    private lateinit var repository: RepositoryImplementation

    @Mock
    private lateinit var log: Log
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainViewModel = MainViewModel(repository)

    }

    @Test
    fun getMovie() {
        verify(repository).fetchMovieData()
        val fetchMovieData = FakeDataDummy().generateMoviesData()
        Thread.sleep(5000)
        val movieData = MutableLiveData<PagedList<RemoteMovieModel.Result>>()
        val mockPagedList = mockPagedList(fetchMovieData)
        movieData.value = mockPagedList
        val observer = mock(Observer::class.java)
        movieData.observeForever(observer as Observer<PagedList<RemoteMovieModel.Result>>)
        val result = LiveDataTestUtil.getValue(movieData)
        assertNotNull(result)
        verify(observer).onChanged(movieData.value)
    }

    @Test
    fun fetchTvShowData() {
        verify(repository).fetchTvShowData()
        val fetchTvShowData = FakeDataDummy().generateTvShowData()
        Thread.sleep(5000)
        val tvShowData = MutableLiveData<PagedList<RemoteTvShowModel.Result>>()
        val mockPagedList = mockPagedList(fetchTvShowData)
        tvShowData.value = mockPagedList
        val observer = mock(Observer::class.java)
        tvShowData.observeForever(observer as Observer<PagedList<RemoteTvShowModel.Result>>)
        val result = LiveDataTestUtil.getValue(tvShowData)
        assertNotNull(result)
        verify(observer).onChanged(tvShowData.value)
    }

    @Test
    fun getFavoriteMovie(){
        verify(repository).favoriteMovieData()
    }

    @Test
    fun getFavoriteTvShow(){
        verify(repository).favoriteTvShowData()
    }

}