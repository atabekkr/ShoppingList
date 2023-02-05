package com.example.shoppinglist.ui.all

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.shoppinglist.R
import com.example.shoppinglist.data.PurchaseDao
import com.example.shoppinglist.data.PurchaseDatabase
import com.example.shoppinglist.data.RollDao
import com.example.shoppinglist.databinding.FragmentItemAllBinding
import com.example.shoppinglist.ui.AllRollAdapter
import kotlinx.coroutines.launch

class AllItemFragment: Fragment(R.layout.fragment_item_all) {
    private lateinit var binding: FragmentItemAllBinding
    private lateinit var db: PurchaseDatabase
    private lateinit var dao: PurchaseDao
    private lateinit var daoRoll: RollDao
    private lateinit var adapter: AllRollAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentItemAllBinding.bind(view)
        db = PurchaseDatabase.getInstance(requireContext())
        dao = db.getPurchaseDao()

        lifecycleScope.launchWhenResumed {

            adapter = AllRollAdapter()

        }

        daoRoll = db.getRollDao()

        lifecycleScope.launchWhenResumed {

            adapter.submitList(daoRoll.getAllRoll())
            binding.recyclerViewAll.adapter = adapter
        }

        binding.apply {

            recyclerViewAll.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

            btnBack.setOnClickListener {
                findNavController().popBackStack(R.id.allPurchasesFragment, false)
            }
        }
    }
}