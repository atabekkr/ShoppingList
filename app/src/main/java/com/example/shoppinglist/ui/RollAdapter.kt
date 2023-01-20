package com.example.shoppinglist.ui

import android.annotation.SuppressLint
import android.graphics.Paint
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.shoppinglist.R
import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.databinding.ItemRollBinding


class RollAdapter: Adapter<RollAdapter.RollViewHolder>() {

    inner class RollViewHolder(private val binding: ItemRollBinding): ViewHolder(binding.root) {

        fun bind(roll: Roll) {
            binding.apply {
                tvName.text = roll.name

                ivMenu.setOnClickListener {
                    onMenuClick.invoke(it, roll, adapterPosition)
                }

                updateStrokeOut(roll, tvName, ckCompleted)

                ckCompleted.setOnCheckedChangeListener { compoundButton, checked ->
                    if (checked) {
                        roll.done = 1
                    } else {
                        roll.done = 0
                    }
//                    ckCompleted.isChecked = checked
                    updateStrokeOut(roll, tvName, ckCompleted)
                    onDoneClick.invoke(roll)
                }

            }
        }

    }

    var items = mutableListOf<Roll>()
        @SuppressLint("NotifyDataSetChanged")
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

    private var onMenuClick: (v: View, roll: Roll, a: Int) -> Unit = {_, _, _ ->}
    fun setOnMenuClickListener(onMenuClick: (v: View, roll: Roll, a: Int) -> Unit) {
        this.onMenuClick = onMenuClick
    }

    fun removeAtPosition(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    private var onDoneClick: (roll: Roll) -> Unit = {}
    fun setOnDoneClick(onDoneClick: (roll: Roll) -> Unit) {
        this.onDoneClick = onDoneClick
    }

    fun updateRoll(position: Int) {
        val currentRoll = items[position]
        val newRoll = currentRoll.copy(done = 1 - currentRoll.done)
        items[position] = newRoll
        notifyItemChanged(position)
    }

    fun updateStrokeOut(roll: Roll, tv: TextView, ck: CheckBox) {
        if (roll.done == 1) {
            ck.isChecked = true
            tv.paintFlags = tv.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            ck.isChecked = false
            tv.paintFlags = tv.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}
