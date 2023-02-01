package com.example.shoppinglist.ui.add

import android.annotation.SuppressLint
import android.app.ProgressDialog.show
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.example.shoppinglist.NewRollAdapter
import com.example.shoppinglist.R
import com.example.shoppinglist.data.PurchaseDatabase
import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.data.RollDao
import com.example.shoppinglist.databinding.FragmentPurchaseRollBinding
import com.example.shoppinglist.ui.RollAdapter
import com.google.android.material.snackbar.Snackbar
import java.util.Collections.addAll
import java.util.Collections.list

class AddRollFragment : Fragment(R.layout.fragment_purchase_roll) {
    private lateinit var binding: FragmentPurchaseRollBinding
    private val adapter = NewRollAdapter()
    private lateinit var db: PurchaseDatabase
    private lateinit var dao: RollDao
    private var listOfRolls = mutableListOf<Roll>()
    private  var idDelete: Int = 0

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPurchaseRollBinding.bind(view)

        db = PurchaseDatabase.getInstance(requireContext())
        dao = db.getRollDao()

        val id = arguments?.getInt("id") ?: 0
        idDelete = id

        listOfRolls.addAll(dao.getRoll(id))

        listOfRolls.sortBy {
            it.done
        }
        adapter.submitList(listOfRolls)

        binding.apply {
            recyclerView.adapter = adapter

            tilField.setEndIconOnClickListener {
                if (etName.text.toString().isNotEmpty()) {
                    val roll = Roll(
                        name = etName.text.toString(), topic_id = id, done = false
                    )
                    dao.addRoll(roll)
                    etName.text?.clear()
                    listOfRolls = dao.getRoll(id)
                    listOfRolls.sortBy { it.done }
                    adapter.submitList(listOfRolls)

                } else {
                    Toast.makeText(requireContext(), "Toltir", Toast.LENGTH_SHORT).show()
                }
            }

            adapter.setOnMenuClickListener { v, roll ->
                show(v, R.menu.menu_purchase, roll)
            }

            adapter.setOnDoneClick { roll ->
                dao.updateRoll(roll)
                
                listOfRolls = (dao.getRoll(id))

                listOfRolls.sortBy { it.id }
                listOfRolls.sortBy { it.done }

                adapter.submitList(listOfRolls)
            }
        }
    }

    private fun show(v: View, @MenuRes menuRes: Int, roll: Roll) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { it ->
            when (it.itemId) {
                R.id.item1 -> {
                    val dialog = EditRollDialog(roll.id, roll.topic_id, roll.name, roll.done)
                    dialog.show(requireActivity().supportFragmentManager, dialog.tag)

                    dialog.setOnEditRollListener {

                        listOfRolls = dao.getRoll(it.topic_id)

                        listOfRolls.sortBy {  roll ->
                            roll.id
                        }

                        listOfRolls.sortBy {  roll ->
                            roll.done
                        }

                        adapter.submitList(listOfRolls)

                        Snackbar.make(v, "Ozgerdi", Snackbar.LENGTH_SHORT).show()
                    }
                }
                R.id.item2 -> {
                    dao.deleteRoll(roll)
                    listOfRolls = dao.getRoll(idDelete)

                    listOfRolls.sortBy {  roll ->
                        roll.id }

                    listOfRolls.sortBy {  roll ->
                        roll.done }

                    adapter.submitList(listOfRolls)
                }
            }
            true

        }

        popup.show()

    }
}