package com.eko.moviesapp.ui.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.eko.moviesapp.R
import com.eko.moviesapp.adapter.MovieAdapter
import com.eko.moviesapp.adapter.StateAdapter
import com.eko.moviesapp.visibleWhen
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private var fetchMovieJob: Job? = null
    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter()
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAdapter()
        fetchMovie()

        movie_retry_button.setOnClickListener {
            movieAdapter.retry()
        }
    }

    private fun initAdapter(){
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        movie_recyclerView.addItemDecoration(dividerItemDecoration)
        movie_recyclerView.adapter = movieAdapter.withLoadStateHeaderAndFooter(
            header = StateAdapter {movieAdapter.retry()},
            footer = StateAdapter {movieAdapter.retry()}
        )

        movieAdapter.addLoadStateListener { loadState ->

            loadState.refresh.let {
                movie_error_msg_textView.visibleWhen(it is LoadState.Error)
                movie_retry_button.visibleWhen(it is LoadState.Error)
                movie_progressBar.visibleWhen(it is LoadState.Loading)
                movie_recyclerView.visibleWhen(it is LoadState.NotLoading)

                if (it is LoadState.Error){
                    movie_error_msg_textView.text = it.error.localizedMessage
                }
            }
        }
    }

    private fun fetchMovie() {
        fetchMovieJob?.cancel()
        fetchMovieJob = lifecycleScope.launch {
            viewModel.movieList().collectLatest {
                movieAdapter.submitData(it)
            }
        }
    }
}
