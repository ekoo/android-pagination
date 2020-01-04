package com.eko.moviesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.eko.moviesapp.R
import com.eko.moviesapp.adapter.MovieAdapter
import com.eko.moviesapp.model.RemoteMovieModel
import com.eko.moviesapp.viewModel.MainViewModel
import com.eko.moviesapp.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movies.*
import org.jetbrains.anko.support.v4.startActivity

class MoviesFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_movies.setHasFixedSize(true)
        viewModel = obtainViewModel(activity!!)
        recyclerViewSetup()
        viewModel.fetchMovie.observe(this, Observer { t ->
            t.let {
                movie_progressBar.visibility = View.GONE
                movieAdapter.submitList(it)
                clickListener()
            }
        })
    }

    private fun clickListener() {
        movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: RemoteMovieModel.Result?) {
                startActivity<MovieDetailActivity>(
                    MovieDetailActivity.EXTRA_DATA to data,
                    MovieDetailActivity.TYPE to 1)
            }
        })
    }

    private fun recyclerViewSetup() {
        rv_movies.apply {
            layoutManager= LinearLayoutManager(activity)
            movieAdapter = MovieAdapter()
            adapter = movieAdapter
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)
    }

}
