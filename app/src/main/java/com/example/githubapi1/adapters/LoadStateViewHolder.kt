package com.example.githubapi1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapi1.R
import com.example.githubapi1.databinding.LoadItemViewBinding

class LoadStateViewHolder(parent: ViewGroup,
                          retry:() -> Unit
): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.load_item_view,parent, false)
) {
    private val binding = LoadItemViewBinding.bind(itemView)
    private val progressBar: ProgressBar = binding.progressBar

    fun bind(loadState: LoadState) {


        progressBar.isVisible = loadState is LoadState.Loading

    }
}