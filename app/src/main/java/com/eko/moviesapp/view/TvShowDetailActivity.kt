package com.eko.moviesapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.eko.moviesapp.R
import com.eko.moviesapp.model.LocalTvShowModel
import com.eko.moviesapp.model.RemoteTvShowModel
import com.eko.moviesapp.viewModel.MainViewModel
import com.eko.moviesapp.viewModel.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.tv_show_detail.*
import org.jetbrains.anko.toast

class TvShowDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tv_show_detail)
        setSupportActionBar(tv_show_detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val type = intent.getIntExtra(TYPE, 0)

        if (type == 1){
            button_delete_tv_show_favorite.visibility = View.GONE
            val data = intent.getParcelableExtra(EXTRA_DATA) as RemoteTvShowModel.Result
            supportActionBar?.title = data.name
            tv_desc.text = data.overview
            tv_date.text = data.firstAirDate
            Picasso.get().load(IMAGE_URL+data.posterPath).into(iv_poster)
            button_save_tv_show_favorite.setOnClickListener {
                fun mapping(data: RemoteTvShowModel.Result): LocalTvShowModel{
                    val id = data.id
                    val name = data.name
                    val firstAirDate = data.firstAirDate
                    val path = data.posterPath
                    val overview = data.overview
                    return LocalTvShowModel(id, name, firstAirDate, path, overview)
                }

                viewModel = obtainViewModel(this)
                viewModel.addFavoriteTvShow(mapping(data))
                toast("Data Succesfully Added")
                finish()
            }
        }

        else if (type == 2){
            button_save_tv_show_favorite.visibility = View.GONE
            val remoteData = intent.getParcelableExtra(EXTRA_DATA) as LocalTvShowModel
            supportActionBar?.title = remoteData.title
            tv_desc.text = remoteData.overview
            tv_date.text = remoteData.releaseDate
            val path = IMAGE_URL+remoteData.posterPath
            Picasso.get().load(path).into(iv_poster)
            button_delete_tv_show_favorite.setOnClickListener {
                viewModel = obtainViewModel(this)
                viewModel.deleteFavoriteTvShow(remoteData)
                toast("Data Deleted")
                finish()
            }
        }





    }
    companion object{
        const val TYPE = "type"
        const val EXTRA_DATA = "tv show list data"
        private const val IMAGE_URL = "https://image.tmdb.org/t/p/w342/"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun obtainViewModel(activity: FragmentActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)
    }
}
