package com.example.shoppinglist.ui.all

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.shoppinglist.R
import com.example.shoppinglist.data.PurchaseDao
import com.example.shoppinglist.data.PurchaseDatabase
import com.example.shoppinglist.data.RollDao
import com.example.shoppinglist.databinding.FragmentItemAllBinding
import com.example.shoppinglist.ui.AllRollAdapter

class AllItemFragment(): Fragment(R.layout.fragment_item_all) {
    private lateinit var binding: FragmentItemAllBinding
    private lateinit var db: PurchaseDatabase
    private lateinit var dao: PurchaseDao
    private lateinit var daoRoll: RollDao
    private val adapter = AllRollAdapter(dao)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentItemAllBinding.bind(view)

        db = PurchaseDatabase.getInstance(requireContext())
        dao = db.getPurchaseDao()
        daoRoll = db.getRollDao()

        adapter.submitList(daoRoll.getAllRoll())

        binding.apply {
            recyclerViewAll.adapter = adapter
        }
    }
}