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
import com.eko.moviesapp.model.RemoteTvShowModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row.view.*

class TvShowAdapter: PagedListAdapter<RemoteTvShowModel.Result, TvShowAdapter.ViewHolder>(
    diffUtilCallback) {

    interface OnItemClickCallback{
        fun onItemClicked(data: RemoteTvShowModel.Result?)
    }
    private lateinit var itemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.itemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShowData = getItem(position)
        Picasso.get().load(IMAGE_URL +tvShowData?.posterPath).into(holder.image)
        holder.title.text = tvShowData?.name
        holder.date.text = tvShowData?.firstAirDate
        holder.itemView.setOnClickListener{itemClickCallback.onItemClicked(tvShowData) }
    }

    companion object{
        private const val IMAGE_URL = "https://image.tmdb.org/t/p/w92/"
        val diffUtilCallback = object : DiffUtil.ItemCallback<RemoteTvShowModel.Result>(){
            override fun areItemsTheSame(
                oldItem: RemoteTvShowModel.Result,
                newItem: RemoteTvShowModel.Result
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: RemoteTvShowModel.Result,
                newItem: RemoteTvShowModel.Result
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val image: ImageView = view.iv_thumbnail
        val title: TextView = view.tv_title
        val date: TextView = view.tv_date

    }

}