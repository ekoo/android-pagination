package com.eko.moviesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eko.moviesapp.R
import com.eko.moviesapp.model.ResponseModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter: PagingDataAdapter<ResponseModel.MovieModel, MovieAdapter.ViewHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val image: ImageView = view.iv_thumbnail
        private val title: TextView = view.tv_title
        private val date: TextView = view.tv_date
        private var movie: ResponseModel.MovieModel? = null

        fun bind(movie: ResponseModel.MovieModel){
            Picasso.get().load(IMAGE_URL +movie.posterPath).into(image)
            title.text = movie.title
            date.text = movie.releaseDate
            this.movie = movie
        }
    }

    companion object{

        private const val IMAGE_URL = "https://image.tmdb.org/t/p/w92/"

        private val diffUtilCallback = object : DiffUtil.ItemCallback<ResponseModel.MovieModel>(){
            override fun areItemsTheSame(oldItem: ResponseModel.MovieModel, newItem: ResponseModel.MovieModel): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: ResponseModel.MovieModel, newItem: ResponseModel.MovieModel): Boolean {
                return oldItem == newItem
            }
        }
    }

}