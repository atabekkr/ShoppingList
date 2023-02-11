package com.example.shoppinglist.ui.all

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.shoppinglist.R
import com.example.shoppinglist.data.Purchase
import com.example.shoppinglist.databinding.FragmentPurchasesAllBinding
import com.example.shoppinglist.ui.PurchaseAdapter
import com.example.shoppinglist.ui.PurchaseViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AllPurchasesFragment : Fragment(R.layout.fragment_purchases_all){
    private lateinit var binding: FragmentPurchasesAllBinding
    private val adapter = PurchaseAdapter()
    private lateinit var viewModel: PurchaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPurchasesAllBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(PurchaseViewModel::class.java)

        initObservers()

        binding.apply {
            recyclerView.adapter = adapter

            adapter.setOnMenuClickListener { v, purchase, position ->
                lifecycleScope.launch {
                    showMenu(v, R.menu.menu_purchase, purchase, position)
                }
            }

            adapter.setOnItemClickListener { purchase, position ->
                lifecycleScope.launch {
                    val bundle = Bundle()
                    bundle.putInt("id", purchase.id)
                    findNavController().navigate(R.id.action_allPurchasesFragment_to_addRollFragment, bundle)
                }

            }

            lifecycleScope.launchWhenResumed {
                viewModel.getAllElements()
            }

            fabAdd.setOnClickListener {
                var k = 0

                if (adapter.models.size != 0) {
                    k = adapter.models.last().id
                    //   val kd = dao.getAllLists().last().id
                }
                val dialog = AddPurchaseDialog(k)
                dialog.show(requireActivity().supportFragmentManager, dialog.tag)

                dialog.setOnAddSuccessListener { id ->
                    findNavController().navigate(
                        AllPurchasesFragmentDirections.actionAllPurchasesFragmentToAddRollFragment(id)
                    )
                }
            }
        }
    }

    private fun showMenu(v: View, @MenuRes menuRes: Int, purchase: Purchase, position: Int) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { menuItem: MenuItem ->

            when (menuItem.itemId) {
                R.id.item1 -> {
                    val bundle = Bundle()
                    bundle.putString("name", purchase.name)

                    val dialog = EditPurchaseDialog(purchase.id, purchase.name, purchase.date)
                    dialog.show(requireActivity().supportFragmentManager, dialog.tag)

                    dialog.setOnEditSuccessListener {
                        lifecycleScope.launch {
                        viewModel.getAllElements()

                        Snackbar.make(requireView(), "Ozgerdi", Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
                R.id.item2 -> {
                    lifecycleScope.launchWhenResumed {
                        viewModel.deletePurchase(purchase)
                        adapter.removeAtPosition(position)
                        Snackbar.make(requireView(), "oshdi", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }

            true
        }

        popup.show()
    }

    private fun initObservers() {
        viewModel.getAllPurchaseFlow.onEach {
            adapter.models = it.toMutableList()
        }.launchIn(lifecycleScope)
    }
}
