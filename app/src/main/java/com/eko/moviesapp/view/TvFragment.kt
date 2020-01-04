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
import com.eko.moviesapp.adapter.TvShowAdapter
import com.eko.moviesapp.model.RemoteTvShowModel
import com.eko.moviesapp.viewModel.MainViewModel
import com.eko.moviesapp.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tv.*
import org.jetbrains.anko.support.v4.startActivity

class TvFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_tv.setHasFixedSize(true)
        viewModel = obtainViewModel(activity!!)
        recyclerViewSetup()
        viewModel.fetchTvShow.observe(this, Observer { t ->
            t.let {
                tv_show_progressBar.visibility = View.GONE
                tvShowAdapter.submitList(it)
                clickListener()
            }
        })
    }

    private fun clickListener() {
        tvShowAdapter.setOnItemClickCallback(object : TvShowAdapter.OnItemClickCallback {
            override fun onItemClicked(data: RemoteTvShowModel.Result?) {
                startActivity<TvShowDetailActivity>(
                    TvShowDetailActivity.EXTRA_DATA to data,
                    TvShowDetailActivity.TYPE to 1)
            }
        })
    }

    private fun recyclerViewSetup() {
        rv_tv.apply {
            layoutManager = LinearLayoutManager(activity)
            tvShowAdapter = TvShowAdapter()
            adapter = tvShowAdapter
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)
    }
}
