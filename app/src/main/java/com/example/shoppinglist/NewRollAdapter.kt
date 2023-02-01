package com.example.shoppinglist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.databinding.ItemRollBinding

class NewRollAdapter: ListAdapter<Roll, NewRollAdapter.RollViewHolder>(DiffCall()) {

    inner class RollViewHolder(private val binding: ItemRollBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(roll: Roll) {
            binding.apply {
                tvName.text = roll.name


                ivMenu.setOnClickListener {
                    onMenuClick.invoke(it, roll)
                }



            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RollViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_roll, parent, false)
        val binding = ItemRollBinding.bind(v)
        return RollViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RollViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    private var onMenuClick: (v: View, roll: Roll) -> Unit = { _, _-> }
    fun setOnMenuClickListener(onMenuClick: (v: View, roll: Roll) -> Unit) {
        this.onMenuClick = onMenuClick
    }
}

private class DiffCall : DiffUtil.ItemCallback<Roll>() {
    override fun areItemsTheSame(oldItem: Roll, newItem: Roll): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Roll, newItem: Roll): Boolean {
        return oldItem == newItem
    }
}