package com.eko.moviesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eko.moviesapp.R
import com.eko.moviesapp.model.LocalMovieModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row.view.*

class FavoriteMovieAdapter: PagedListAdapter<LocalMovieModel, FavoriteMovieAdapter.ViewHolder>(
    diffUtilCallback) {

    companion object{
        private const val IMAGE_URL = "https://image.tmdb.org/t/p/w92/"
        val diffUtilCallback = object : DiffUtil.ItemCallback<LocalMovieModel>(){
            override fun areItemsTheSame(oldItem: LocalMovieModel, newItem: LocalMovieModel): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: LocalMovieModel, newItem: LocalMovieModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: LocalMovieModel?)
    }
    private lateinit var itemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.itemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        Picasso.get().load(IMAGE_URL+movie?.posterPath).into(holder.image)
        holder.title.text = movie?.title
        holder.date.text = movie?.releaseDate
        holder.itemView.setOnClickListener{itemClickCallback.onItemClicked(movie) }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val image: ImageView = view.iv_thumbnail
        val title: TextView = view.tv_title
        val date: TextView = view.tv_date

    }

}