package com.eko.moviesapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.eko.moviesapp.R
import com.eko.moviesapp.model.LocalMovieModel
import com.eko.moviesapp.model.RemoteMovieModel
import com.eko.moviesapp.viewModel.MainViewModel
import com.eko.moviesapp.viewModel.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_detail.*
import org.jetbrains.anko.toast


class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail)
        setSupportActionBar(movie_detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val type = intent.getIntExtra(TYPE, 0)

        if (type == 1){
            button_delete_movie_favorite.visibility = View.GONE
            val remoteData = intent.getParcelableExtra(EXTRA_DATA) as RemoteMovieModel.Result
            supportActionBar?.title = remoteData.title
            tv_desc.text = remoteData.overview
            tv_date.text = remoteData.releaseDate
            val path = IMAGE_URL+remoteData.posterPath
            Picasso.get().load(path).into(iv_poster)
            button_save_movie_favorite.setOnClickListener {

                fun mapping(data: RemoteMovieModel.Result): LocalMovieModel{
                    val id = data.id
                    val title = data.title
                    val date = data.releaseDate
                    val posterPath = data.posterPath
                    val overview = data.overview
                    return LocalMovieModel(id, title, date, posterPath, overview)
                }

                viewModel = obtainViewModel(this)
                viewModel.addFavoriteMovie(mapping(remoteData))
                toast("Data Succesfully Added")
                finish()
            }


        }

        else if (type == 2){
            button_save_movie_favorite.visibility = View.GONE
            val remoteData = intent.getParcelableExtra(EXTRA_DATA) as LocalMovieModel
            supportActionBar?.title = remoteData.title
            tv_desc.text = remoteData.overview
            tv_date.text = remoteData.releaseDate
            val path = IMAGE_URL+remoteData.posterPath
            Picasso.get().load(path).into(iv_poster)
            button_delete_movie_favorite.setOnClickListener {
                viewModel = obtainViewModel(this)
                viewModel.deleteFavoriteMovie(remoteData)
                toast("Data Deleted")
                finish()
            }
        }

    }
    companion object{
        const val TYPE = "type"
        const val EXTRA_DATA = "movie list data"
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
