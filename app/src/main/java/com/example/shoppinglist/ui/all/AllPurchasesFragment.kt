package com.example.shoppinglist.ui.all

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.shoppinglist.R
import com.example.shoppinglist.data.PurchaseDao
import com.example.shoppinglist.data.PurchaseDatabase
import com.example.shoppinglist.databinding.FragmentPurchasesAllBinding
import com.example.shoppinglist.databinding.ItemPurchaseBinding
import com.example.shoppinglist.ui.PurchaseAdapter
import com.example.shoppinglist.ui.add.AddPurchaseFragment

class AllPurchasesFragment: Fragment(R.layout.fragment_purchases_all) {
    private lateinit var binding: FragmentPurchasesAllBinding
    private val adapter = PurchaseAdapter()
    private lateinit var db: PurchaseDatabase
    private lateinit var dao: PurchaseDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPurchasesAllBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        db = PurchaseDatabase.getInstance(requireContext())
        dao = db.getPurchaseDao()

        adapter.models = dao.getAllLists().toMutableList()

        binding.apply {
            recyclerView.adapter = adapter

            fabAdd.setOnClickListener {
                val dialog = AddPurchaseDialog()
                dialog.show(requireActivity().supportFragmentManager, dialog.tag)

                dialog.setOnAddSuccessListener {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, AddPurchaseFragment())
                        .addToBackStack(AddPurchaseFragment::class.java.simpleName)
                        .commit()

                    adapter.models = dao.getAllLists().toMutableList()
                }
            }
        }
    }
}
