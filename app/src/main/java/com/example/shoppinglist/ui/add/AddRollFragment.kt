package com.example.shoppinglist.ui.add

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.example.shoppinglist.R
import com.example.shoppinglist.data.PurchaseDatabase
import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.data.RollDao
import com.example.shoppinglist.databinding.FragmentPurchaseAddBinding
import com.example.shoppinglist.ui.RollAdapter
import com.google.android.material.snackbar.Snackbar

class AddRollFragment: Fragment(R.layout.fragment_purchase_add) {
    private lateinit var binding: FragmentPurchaseAddBinding
    private val adapter = RollAdapter()
    private lateinit var db: PurchaseDatabase
    private lateinit var dao: RollDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPurchaseAddBinding.bind(view)

        db = PurchaseDatabase.getInstance(requireContext())
        dao = db.getRollDao()

        val id = arguments?.getInt("id") ?: 0
        println("id*********************************** $id")

        adapter.items = dao.getRoll(id)


        binding.apply {
            recyclerView.adapter = adapter

            tilField.setEndIconOnClickListener {
                if (etName.text.toString().isNotEmpty()) {
                    val roll = Roll(
                        name = etName.text.toString(),
                        topic_id = id,
                        done = 0
                    )
                    dao.addRoll(roll)
                    etName.text?.clear()
                    adapter.items = dao.getRoll(id)
                } else {
                    Toast.makeText(requireContext(), "Toltir", Toast.LENGTH_SHORT).show()
                }
            }
            adapter.setOnMenuClickListener { v, roll, position ->
                show(v, R.menu.menu_purchase, roll, position)
            }

            adapter.setOnDoneClick { roll ->
                dao.updateRoll(roll)
            }
        }
    }

    private fun show(v: View, @MenuRes menuRes: Int, roll: Roll, position: Int) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.item1 -> {
                    val dialog = EditRollDialog(roll.id, roll.topic_id, roll.name, roll.done)
                    dialog.show(requireActivity().supportFragmentManager, dialog.tag)

                    dialog.setOnEditRollListener {
                        adapter.items = dao.getAllRoll().toMutableList()

                        Snackbar.make(v, "Ozgerdi", Snackbar.LENGTH_SHORT).show()
                    }
                }
                R.id.item2 -> {
                    dao.deleteRoll(roll)
                    adapter.removeAtPosition(position)
                }
            }
            true

        }

        popup.show()

    }
}