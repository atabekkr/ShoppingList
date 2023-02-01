package com.example.shoppinglist.ui

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox

import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.SortedList
import com.example.shoppinglist.R
import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.databinding.ItemRollBinding
import com.example.shoppinglist.ui.RollAdapter.RollViewHolder
import com.example.shoppinglist.ui.add.DiffCallback
import java.util.Calendar


class RollAdapter : ListAdapter<Roll, RollViewHolder>(DiffCall()) {

    inner class RollViewHolder(private val binding: ItemRollBinding) : ViewHolder(binding.root) {

        fun bind(roll: Roll) {
            binding.apply {
                tvName.text = roll.name

                ivMenu.setOnClickListener {
                    onMenuClick.invoke(it, roll, adapterPosition)
                }
                ckCompleted.isChecked = roll.done
                updateStrokeOut(roll, tvName)

                ckCompleted.setOnCheckedChangeListener { _, checked ->
                    roll.done = checked
                    updateStrokeOut(roll, tvName)
                    onDoneClick?.invoke(roll, checked)
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

    private var onMenuClick: (v: View, roll: Roll, a: Int) -> Unit = { _, _, _ -> }
    fun setOnMenuClickListener(onMenuClick: (v: View, roll: Roll, a: Int) -> Unit) {
        this.onMenuClick = onMenuClick
    }

    private var onDoneClick: ((roll: Roll, isDone: Boolean) -> Unit)? = null
    fun setOnDoneClick(onDoneClick: (roll: Roll, isDone: Boolean) -> Unit) {
        this.onDoneClick = onDoneClick
    }

    private fun updateStrokeOut(roll: Roll, tv: TextView) {
        if (roll.done) {
            tv.paintFlags = tv.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tv.paintFlags = tv.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}

private class DiffCall : DiffUtil.ItemCallback<Roll>() {
    override fun areItemsTheSame(oldItem: Roll, newItem: Roll): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Roll, newItem: Roll): Boolean {
        return oldItem.id == newItem.id && oldItem.name == newItem.name
    }
}
