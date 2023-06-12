package com.scan.unplashapi.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fahamin.unplashapi_jetpack.UnsplashModel
import com.scan.unplashapi.databinding.AdapterUnplashBinding

class UnsplashModelPagerAdapter: PagingDataAdapter<UnsplashModel, UnsplashModelPagerAdapter.UnsplashModelViewHolder>(UnsplashModelComparator) {

    override fun onBindViewHolder(holder: UnsplashModelViewHolder, position: Int) {
        val UnsplashModel = getItem(position)!!
        holder.view.name.text = UnsplashModel.createdAt
        //Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/w300"+UnsplashModel.poster_path).into(holder.view.imageview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnsplashModelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterUnplashBinding.inflate(inflater, parent, false)
        return UnsplashModelViewHolder(binding)
    }

    class UnsplashModelViewHolder(val view: AdapterUnplashBinding): RecyclerView.ViewHolder(view.root) {

    }

    object UnsplashModelComparator: DiffUtil.ItemCallback<UnsplashModel>() {
        override fun areItemsTheSame(oldItem: UnsplashModel, newItem: UnsplashModel): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UnsplashModel, newItem: UnsplashModel): Boolean {
            return oldItem == newItem
        }
    }
}

