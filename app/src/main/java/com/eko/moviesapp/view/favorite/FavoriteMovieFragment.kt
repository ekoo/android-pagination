package com.eko.moviesapp.view.favorite


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
import com.eko.moviesapp.adapter.FavoriteMovieAdapter
import com.eko.moviesapp.model.LocalMovieModel
import com.eko.moviesapp.view.MovieDetailActivity
import com.eko.moviesapp.viewModel.MainViewModel
import com.eko.moviesapp.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import org.jetbrains.anko.support.v4.startActivity

class FavoriteMovieFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var movieAdapter: FavoriteMovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_favorite_movie.setHasFixedSize(true)
        viewModel = obtainViewModel(activity!!)
        recyclerViewSetup()
        viewModel.favoriteMovie.observe(this, Observer { t ->
            t.let {
                movieAdapter.submitList(it)
                clickListener()
            }
        })
    }

    private fun recyclerViewSetup() {
        rv_favorite_movie.apply {
            layoutManager= LinearLayoutManager(activity)
            movieAdapter = FavoriteMovieAdapter()
            adapter = movieAdapter
        }
    }

    private fun clickListener() {
        movieAdapter.setOnItemClickCallback(object : FavoriteMovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: LocalMovieModel?) {
                startActivity<MovieDetailActivity>(
                    MovieDetailActivity.EXTRA_DATA to data,
                    MovieDetailActivity.TYPE to 2
                    )
            }
        })
    }

    private fun obtainViewModel(activity: FragmentActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)
    }
}
