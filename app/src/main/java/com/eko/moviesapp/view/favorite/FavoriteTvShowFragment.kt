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
import com.eko.moviesapp.adapter.FavoriteTvShowAdapter
import com.eko.moviesapp.model.LocalTvShowModel
import com.eko.moviesapp.view.TvShowDetailActivity
import com.eko.moviesapp.viewModel.MainViewModel
import com.eko.moviesapp.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.*
import org.jetbrains.anko.support.v4.startActivity

class FavoriteTvShowFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var tvShowAdapter: FavoriteTvShowAdapter
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_favorite_tv_show.setHasFixedSize(true)
        viewModel = obtainViewModel(activity!!)
        recyclerViewSetup()
        viewModel.favoriteTvShow.observe(this, Observer { t ->
            t.let {
                tvShowAdapter.submitList(it)
                clickListener()
            }
        })
    }

    private fun recyclerViewSetup() {
        rv_favorite_tv_show.apply {
            layoutManager= LinearLayoutManager(activity)
            tvShowAdapter = FavoriteTvShowAdapter()
            adapter = tvShowAdapter
        }
    }

    private fun clickListener() {
        tvShowAdapter.setOnItemClickCallback(object : FavoriteTvShowAdapter.OnItemClickCallback {
            override fun onItemClicked(data: LocalTvShowModel?) {
                startActivity<TvShowDetailActivity>(
                    TvShowDetailActivity.EXTRA_DATA to data,
                    TvShowDetailActivity.TYPE to 2
                )
            }
        })
    }

    private fun obtainViewModel(activity: FragmentActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)
    }
}
