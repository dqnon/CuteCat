package com.example.cutecat.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cutecat.R
import com.example.cutecat.databinding.CatsListItemBinding
import com.example.cutecat.model.cat.CatItem
import com.squareup.picasso.Picasso

class CatAdapter(val listener: Listener): ListAdapter<CatItem, CatAdapter.CatItemViewHolder>(Comparator()) {
    class CatItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = CatsListItemBinding.bind(view)

        fun bind(cat: CatItem, listener: Listener) = with(binding){
            Picasso.get().load(cat.url).into(imCat)
            imCat.setOnClickListener {
                listener.onClick(cat)
            }
        }
    }

    class Comparator: DiffUtil.ItemCallback<CatItem>() {
        override fun areItemsTheSame(oldItem: CatItem, newItem: CatItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CatItem, newItem: CatItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cats_list_item, parent, false)
        return CatItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatItemViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    interface Listener{
        fun onClick(cat: CatItem)
    }
}