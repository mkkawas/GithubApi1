package com.example.githubapi1.paging

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapi1.databinding.ItemViewBinding
import com.example.githubapi1.modelsKoosa.GetReposModel

class MainListAdapter : PagingDataAdapter<GetReposModel, MainListAdapter.ViewHolder>(DiffCallBack) {

    class ViewHolder(private val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GetReposModel) {
            with(binding) {
                name.text = item.name
                Log.d("name", name.text.toString())
                itemId.text = item.id
                desc.text = item.description
                Glide.with(itemView.context)
                    .load(item.owner?.avatar_url)
                    .into(itemImage)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        binding.apply{
//
//            name.text = getItem(position)?.name
//            Log.d("name", name.text.toString())
//
//            itemId.text = getItem(position)?.id
//            desc.text = if(getItem(position)?.description == null)
//                "No Description Available" else getItem(position)?.description
//
//            Glide.with(holder.itemView.context)
//                .load(getItem(position)?.owner?.avatar_url)
//                .into(itemImage)
//        }
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false

            )
        )
    }

    object DiffCallBack : DiffUtil.ItemCallback<GetReposModel>() {
        override fun areItemsTheSame(oldItem: GetReposModel, newItem: GetReposModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GetReposModel, newItem: GetReposModel): Boolean {
            return oldItem == newItem
        }

    }


}