package com.example.shoppinglist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.shoppinglist.R
import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.databinding.ItemRollBinding

class RollAdapter: Adapter<RollAdapter.RollViewHolder>() {

    inner class RollViewHolder(private val binding: ItemRollBinding): ViewHolder(binding.root) {
        fun bind(roll: Roll) {
            binding.tvName.text = roll.name
        }
    }

    var items = mutableListOf<Roll>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RollViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_roll, parent, false)
        val binding = ItemRollBinding.bind(v)
        return RollViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RollViewHolder, position: Int) {
        holder.bind(items[position])
    }
}