package com.example.shoppinglist.ui

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.shoppinglist.R
import com.example.shoppinglist.data.*
import com.example.shoppinglist.databinding.ItemAllBinding
import com.example.shoppinglist.ui.AllRollAdapter.InnerViewHolder

class AllRollAdapter(private val dao: PurchaseDao): ListAdapter<Roll, InnerViewHolder>(DiffUtilCallBack()) {

    inner class InnerViewHolder(private val binding: ItemAllBinding): ViewHolder(binding.root) {
        fun bind(roll: Roll) {
            binding.apply {
                tvNameAll.text = roll.name
                tvPurchase.text =  dao.getPurchase(roll.topic_id).name
            }

            updateStrokeOut(roll, binding.tvNameAll)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_all, parent, false)
        val binding = ItemAllBinding.bind(v)
        return InnerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun updateStrokeOut(roll: Roll, tv: TextView) {
        if (roll.done) {
            tv.isEnabled = false
            tv.paintFlags = tv.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tv.paintFlags = tv.paintFlags and android.graphics.Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}

private class DiffUtilCallBack: DiffUtil.ItemCallback<Roll>() {
    override fun areItemsTheSame(oldItem: Roll, newItem: Roll): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Roll, newItem: Roll): Boolean {
        return oldItem == newItem
    }
}