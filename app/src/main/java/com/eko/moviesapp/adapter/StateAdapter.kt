package com.eko.moviesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eko.moviesapp.R
import com.eko.moviesapp.visibleWhen
import kotlinx.android.synthetic.main.item_load_state.view.*

class StateAdapter(private val retry: () -> Unit) : LoadStateAdapter<StateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_load_state, parent, false), retry)
    }

    inner class LoadStateViewHolder(view: View, retry: () -> Unit): RecyclerView.ViewHolder(view){
        init {
            itemView.state_retry_button.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState){
            itemView.apply {
                state_progressBar.visibleWhen(loadState is LoadState.Loading)
                state_error_msg_textView.visibleWhen(loadState is LoadState.Error)
                state_retry_button.visibleWhen(loadState is LoadState.Error)
            }

            if (loadState is LoadState.Error){
                itemView.state_error_msg_textView.text = loadState.error.localizedMessage
            }
        }
    }

}