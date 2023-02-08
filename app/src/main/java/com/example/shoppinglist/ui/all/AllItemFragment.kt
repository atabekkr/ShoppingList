package com.example.shoppinglist.ui.all

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.shoppinglist.MainViewModel
import com.example.shoppinglist.R
import com.example.shoppinglist.data.PurchaseDao
import com.example.shoppinglist.data.PurchaseDatabase
import com.example.shoppinglist.data.RollDao
import com.example.shoppinglist.databinding.FragmentItemAllBinding
import com.example.shoppinglist.ui.AllRollAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AllItemFragment: Fragment(R.layout.fragment_item_all) {
    private lateinit var binding: FragmentItemAllBinding
    private lateinit var adapter: AllRollAdapter
    private lateinit var viewModel: MainViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentItemAllBinding.bind(view)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        ).get(MainViewModel::class.java)

        initObservers()

        adapter = AllRollAdapter()

        lifecycleScope.launchWhenResumed {

            viewModel.getAllRolls()
            binding.recyclerViewAll.adapter = adapter
        }

        binding.apply {

            recyclerViewAll.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

            btnBack.setOnClickListener {
                findNavController().popBackStack(R.id.allPurchasesFragment, false)
            }
        }
    }

    private fun initObservers() {
        viewModel.getAllRollFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)
    }
}