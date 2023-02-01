package com.example.shoppinglist.ui.add

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.databinding.ItemRollBinding

class SampleListAdapter : ListAdapter<Roll, SampleListAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        val v = LayoutInflater.from(parent.context)
        .inflate(R.layout.item_roll, parent, false)
        val binding = ItemRollBinding.bind(v)
        return ItemViewholder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewholder(private val binding: ItemRollBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(roll: Roll)  {
            binding.tvName.text = roll.name
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Roll>() {

    override fun areItemsTheSame(oldItem: Roll, newItem: Roll): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Roll, newItem: Roll): Boolean {
        return oldItem == newItem
    }
}