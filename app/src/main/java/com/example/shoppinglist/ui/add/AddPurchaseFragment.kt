package com.example.shoppinglist.ui.add

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.shoppinglist.R
import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.data.RollDao
import com.example.shoppinglist.data.RollDatabase
import com.example.shoppinglist.databinding.DialogPurchaseAddBinding
import com.example.shoppinglist.databinding.FragmentPurchaseAddBinding
import com.example.shoppinglist.ui.RollAdapter

class AddPurchaseFragment: Fragment(R.layout.fragment_purchase_add) {
    private lateinit var binding: FragmentPurchaseAddBinding
    private val adapter = RollAdapter()
    private lateinit var db: RollDatabase
    private lateinit var dao: RollDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPurchaseAddBinding.bind(view)

        db = RollDatabase.getInstance(requireContext())
        dao = db.getRollDao()

        adapter.items = dao.getAllRoll().toMutableList()

        binding.apply {
            recyclerView.adapter = adapter

            tilField.setEndIconOnClickListener {
                if (etName.text.toString().isNotEmpty()) {
                    val roll = Roll(
                        name = etName.text.toString()
                    )
                    dao.addRoll(roll)
                    etName.text?.clear()
                    adapter.items = dao.getAllRoll().toMutableList()
                } else {
                    Toast.makeText(requireContext(), "Toltirsay", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}