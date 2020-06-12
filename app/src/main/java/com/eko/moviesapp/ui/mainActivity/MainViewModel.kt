package com.eko.moviesapp.ui.mainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.eko.moviesapp.repository.MainRepository

class MainViewModel(private val repository: MainRepository = MainRepository()): ViewModel() {

    fun movieList() = repository.getMovie().cachedIn(viewModelScope)

}