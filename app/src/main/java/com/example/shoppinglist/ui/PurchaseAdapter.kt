package com.example.shoppinglist.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.shoppinglist.R
import com.example.shoppinglist.data.Purchase
import com.example.shoppinglist.databinding.ItemPurchaseBinding

class PurchaseAdapter: Adapter<PurchaseAdapter.PurchaseViewHolder>() {

    inner class PurchaseViewHolder(private val binding: ItemPurchaseBinding): ViewHolder(binding.root) {
        fun bind(purchase: Purchase) {
            binding.apply {
                tvName.text = purchase.name

                cvPurchase.setOnClickListener {
                    onItemClick.invoke(purchase, adapterPosition)
                }

                ivMenu.setOnClickListener { v  ->
                    onMenuClick.invoke(v)
                }
            }
        }
    }

    var models = mutableListOf<Purchase>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = models.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_purchase, parent, false)
        val binding = ItemPurchaseBinding.bind(v)
        return PurchaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        holder.bind(models[position])
    }

    private var onItemClick: (purchase: Purchase, position: Int) -> Unit = {_, _ ->}
    fun setOnItemClickListener(onItemClick: (purchase: Purchase, position: Int) -> Unit) {
        this.onItemClick = onItemClick
    }

    private var onMenuClick: (v: View) -> Unit = {}
    fun setOnMenuClickListener(onMenuClick: (v: View) -> Unit) {
        this.onMenuClick = onMenuClick
    }
}