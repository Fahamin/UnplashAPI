package com.scan.unplashapi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fahamin.unplashapi_jetpack.UnsplashModel
import com.scan.unplashapi.databinding.AdapterUnplashBinding

class UnplashAdapter :
    PagingDataAdapter<UnsplashModel, UnplashAdapter.UnPlashViewHolder>(UnsplashModelComparator) {

    class UnPlashViewHolder(val view: AdapterUnplashBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onBindViewHolder(holder: UnPlashViewHolder, position: Int) {
        val model = getItem(position);
        holder.view.name.text = model?.createdAt
        Glide.with(holder.itemView.context)
            .load(model?.urls?.small.toString())
            .into(holder.view.imageview)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnPlashViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AdapterUnplashBinding.inflate(layoutInflater, parent, false)
        return UnPlashViewHolder(binding)
    }

    object UnsplashModelComparator : DiffUtil.ItemCallback<UnsplashModel>() {
        override fun areItemsTheSame(oldItem: UnsplashModel, newItem: UnsplashModel): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UnsplashModel, newItem: UnsplashModel): Boolean {
            return oldItem == newItem
        }
    }
}