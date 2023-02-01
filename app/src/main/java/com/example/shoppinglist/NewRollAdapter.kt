package com.example.shoppinglist

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

                ckCompleted.isChecked = roll.done
                updateStrokeOut(roll, tvName)

                /*ckCompleted.setOnCheckedChangeListener { _, checked ->
                    roll.done = checked
                    updateStrokeOut(roll, tvName)
                    onDoneClick?.invoke(roll, checked)
                }*/

                ckCompleted.setOnClickListener {
                    roll.done = ckCompleted.isChecked
                    updateStrokeOut(roll, tvName)
                    onDoneClick?.invoke(roll)
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

    private var onDoneClick: ((roll: Roll) -> Unit)? = null
    fun setOnDoneClick(onDoneClick: (roll: Roll) -> Unit) {
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
        return oldItem == newItem
    }
}
