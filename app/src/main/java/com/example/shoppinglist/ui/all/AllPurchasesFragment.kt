package com.example.shoppinglist.ui.all

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment
import com.example.shoppinglist.R
import com.example.shoppinglist.data.Purchase
import com.example.shoppinglist.data.PurchaseDao
import com.example.shoppinglist.data.PurchaseDatabase
import com.example.shoppinglist.data.RollDao
import com.example.shoppinglist.databinding.FragmentPurchasesAllBinding
import com.example.shoppinglist.ui.PurchaseAdapter
import com.example.shoppinglist.ui.add.AddPurchaseFragment
import com.google.android.material.snackbar.Snackbar

class AllPurchasesFragment: Fragment(R.layout.fragment_purchases_all) {
    private lateinit var binding: FragmentPurchasesAllBinding
    private val adapter = PurchaseAdapter()
    private lateinit var db: PurchaseDatabase
    private lateinit var dao: PurchaseDao
    private lateinit var rollDao: RollDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPurchasesAllBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        db = PurchaseDatabase.getInstance(requireContext())
        dao = db.getPurchaseDao()
        rollDao = db.getRollDao()

        binding.apply {
            recyclerView.adapter = adapter

            adapter.models = dao.getAllLists().toMutableList()

            adapter.setOnMenuClickListener { v, purchase, position ->
                showMenu(v, R.menu.menu_purchase, purchase, position)
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
                val dialog = AddPurchaseDialog(adapter.models.lastIndex)
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

    private fun showMenu(v: View, @MenuRes menuRes: Int, purchase: Purchase, position: Int) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            when(menuItem.itemId) {
                R.id.item1 -> {
                    val bundle = Bundle()
                    bundle.putString("name", purchase.name)

                    val dialog = EditPurchaseDialog(purchase.id, purchase.name)
                    dialog.show(requireActivity().supportFragmentManager, dialog.tag)

                    dialog.setOnEditSuccessListener {
                        adapter.models = dao.getAllLists().toMutableList()

                        Snackbar.make(requireView(), "Ozgerdi", Snackbar.LENGTH_SHORT).show()
                    }

                   /* MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Edit Contact")
                        .setMessage("${purchase.name} di ozgerteyikpa")
                        .setPositiveButton("Yes") { dialog, _ ->
                            dao.updatePurchase(purchase)
                            Snackbar.make(v, "Purchase edit successfully", Snackbar.LENGTH_SHORT)
                                .show()
                            dialog.dismiss()
                        }
                        .setNegativeButton("Cancel") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()*/
                }
                R.id.item2 -> {
                    dao.deletePurchase(purchase)
                    adapter.removeAtPosition(position)

                    rollDao.getRoll(purchase.id).forEach {
                        rollDao.deleteRoll(it)
                    }
                    Snackbar.make(requireView(), "oshdi", Snackbar.LENGTH_SHORT).show()
                }
            }
            true
         }

        popup.show()
    }
}
