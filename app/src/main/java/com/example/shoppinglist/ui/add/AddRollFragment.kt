package com.example.shoppinglist.ui.add

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.shoppinglist.R
import com.example.shoppinglist.data.PurchaseDao
import com.example.shoppinglist.data.PurchaseDatabase
import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.data.RollDao
import com.example.shoppinglist.databinding.FragmentPurchaseRollBinding
import com.example.shoppinglist.ui.NewRollAdapter
import com.google.android.material.snackbar.Snackbar


@Suppress("UNUSED_EXPRESSION")
class AddRollFragment : Fragment(R.layout.fragment_purchase_roll) {
    private lateinit var binding: FragmentPurchaseRollBinding
    private val adapter = NewRollAdapter()
    private lateinit var db: PurchaseDatabase
    private lateinit var dao: RollDao
    private lateinit var daoPurchase: PurchaseDao
    private var listOfRolls = mutableListOf<Roll>()
    private val navArgs: AddRollFragmentArgs by navArgs()

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPurchaseRollBinding.bind(view)

        db = PurchaseDatabase.getInstance(requireContext())
        dao = db.getRollDao()
        daoPurchase = db.getPurchaseDao()

        binding.recyclerView.adapter = adapter

        val id = navArgs.id
        lifecycleScope.launchWhenResumed {
        listOfRolls.addAll(dao.getRoll(id))

            listOfRolls.sortBy {
                it.done
            }
            adapter.submitList(listOfRolls)
        }


        binding.apply {

            lifecycleScope.launchWhenResumed {
                tvName.text = daoPurchase.getPurchase(id).name
            }

            tilField.setEndIconOnClickListener {
                lifecycleScope.launchWhenResumed {
                    if (etName.text.toString().isNotEmpty()) {
                        val roll = Roll(
                            name = etName.text.toString(),
                            topic_id = id,
                            done = false,
                            purchaseName = daoPurchase.getPurchase(id).name
                        )
                        dao.addRoll(roll)
                        etName.text?.clear()
                        /*val imm: InputMethodManager?
                            getSystemService<Any>(requireContext()) as InputMethodManager?
                        imm?.hideSoftInputFromWindow(
                            tilField.windowToken,
                            InputMethodManager.HIDE_NOT_ALWAYS
                        )*/
                        etName.clearFocus()
                        listOfRolls = dao.getRoll(id)
                        listOfRolls.sortBy { it.done }
                        adapter.submitList(listOfRolls)

                    } else {
                        Toast.makeText(requireContext(), "Toltir", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            adapter.setOnMenuClickListener { v, roll ->
                lifecycleScope.launchWhenResumed {
                    show(v, R.menu.menu_purchase, roll)
                }
            }

            adapter.setOnDoneClick { roll ->
                lifecycleScope.launchWhenResumed {
                    dao.updateRoll(roll)

                    listOfRolls = dao.getRoll(id)

                    listOfRolls.sortBy { it.id }
                    listOfRolls.sortBy { it.done }

                    adapter.submitList(listOfRolls)
                }
            }

            btnBack.setOnClickListener {
                findNavController().popBackStack(R.id.allPurchasesFragment, false)
            }
        }
    }

    private fun show(v: View, @MenuRes menuRes: Int, roll: Roll) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { it ->

            when (it.itemId) {
                R.id.item1 -> {
                    val dialog = EditRollDialog(roll.id, roll.topic_id, roll.name, roll.done, roll.purchaseName)
                    dialog.show(requireActivity().supportFragmentManager, dialog.tag)

                    dialog.setOnEditRollListener {
                        lifecycleScope.launchWhenResumed {

                            listOfRolls = dao.getRoll(it.topic_id)

                            listOfRolls.sortBy {  roll ->
                                roll.id
                            }

                            listOfRolls.sortBy {  roll ->
                                roll.done
                            }

                            adapter.submitList(listOfRolls)

                            if (roll.name != it.name) {
                                Snackbar.make(v, "Ozgerdi", Snackbar.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
                R.id.item2 -> {
                    lifecycleScope.launchWhenResumed {
                        dao.deleteRoll(roll)
                        listOfRolls = dao.getRoll(roll.topic_id)

                        listOfRolls.sortBy {  roll ->
                            roll.id }

                        listOfRolls.sortBy {  roll ->
                            roll.done }

                        adapter.submitList(listOfRolls)
                    }
                }
            }
            true

        }

        popup.show()
    }

}

