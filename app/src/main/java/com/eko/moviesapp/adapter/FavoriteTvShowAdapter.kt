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
import com.eko.moviesapp.model.LocalTvShowModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row.view.*

class FavoriteTvShowAdapter: PagedListAdapter<LocalTvShowModel, FavoriteTvShowAdapter.ViewHolder>(
    diffUtilCallback) {

    companion object{
        private const val IMAGE_URL = "https://image.tmdb.org/t/p/w92/"
        val diffUtilCallback = object : DiffUtil.ItemCallback<LocalTvShowModel>(){
            override fun areItemsTheSame(oldItem: LocalTvShowModel, newItem: LocalTvShowModel): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: LocalTvShowModel, newItem: LocalTvShowModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: LocalTvShowModel?)
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
        val tvShowModel = getItem(position)

        Picasso.get().load(IMAGE_URL+tvShowModel?.posterPath).into(holder.image)
        holder.title.text = tvShowModel?.title
        holder.date.text = tvShowModel?.releaseDate
        holder.itemView.setOnClickListener{itemClickCallback.onItemClicked(tvShowModel) }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val image: ImageView = view.iv_thumbnail
        val title: TextView = view.tv_title
        val date: TextView = view.tv_date

    }

}