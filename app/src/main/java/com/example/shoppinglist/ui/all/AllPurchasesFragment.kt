package com.example.shoppinglist.ui.all

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment
import com.example.shoppinglist.R
import com.example.shoppinglist.data.PurchaseDao
import com.example.shoppinglist.data.PurchaseDatabase
import com.example.shoppinglist.databinding.FragmentPurchasesAllBinding
import com.example.shoppinglist.databinding.ItemPurchaseBinding
import com.example.shoppinglist.ui.PurchaseAdapter
import com.example.shoppinglist.ui.add.AddPurchaseFragment
import com.google.android.material.snackbar.Snackbar

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

        binding.apply {
            recyclerView.adapter = adapter

            adapter.models = dao.getAllLists().toMutableList()

            adapter.setOnMenuClickListener {
                showMenu(it, R.menu.menu_purchase)
            }

            adapter.setOnItemClickListener { purchase, position ->
                val bundle = Bundle()
                bundle.putInt("id", purchase.id)

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, AddPurchaseFragment::class.java, bundle)
                    .addToBackStack(AllPurchasesFragment::class.java.simpleName)
                    .commit()
            }

            fabAdd.setOnClickListener {
                val dialog = AddPurchaseDialog(adapter.models.size)
                dialog.show(requireActivity().supportFragmentManager, dialog.tag)

                dialog.setOnAddSuccessListener { id ->
                    val bundle = Bundle()
                    bundle.putInt("id", id)

                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, AddPurchaseFragment::class.java, bundle)
                        .addToBackStack(AddPurchaseFragment::class.java.simpleName)
                        .commit()

                    adapter.models = dao.getAllLists().toMutableList()
                }
            }
        }
    }

    private fun showMenu(v: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            when(menuItem.itemId) {
                R.id.item1 -> {
                    Snackbar.make(requireView(), "ozgert", Snackbar.LENGTH_SHORT).show()
                }
                R.id.item2 -> {
                    Snackbar.make(requireView(), "oshir", Snackbar.LENGTH_SHORT).show()
                }
            }
            true
         }

        popup.show()
    }
}
