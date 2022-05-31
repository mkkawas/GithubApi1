package com.example.githubapi1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapi1.modelsKoosa.GetReposModel
import com.example.githubapi1.databinding.ItemViewBinding


class reposAdapter(val items: List<GetReposModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)

  private lateinit var binding: ItemViewBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
       return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder) {


            with(items[position]) {
                binding.desc.text = description
                binding.itemId.text = id
                binding.name.text = name
                Glide.with(holder.itemView.context)
                    .load(owner?.avatar_url)
                    .into(binding.itemImage)


            }
        }

    }
    override fun getItemCount() = items.size

}