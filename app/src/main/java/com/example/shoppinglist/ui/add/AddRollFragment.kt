package com.example.shoppinglist.ui.add

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.shoppinglist.ui.RollViewModel
import com.example.shoppinglist.R
import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.databinding.FragmentPurchaseRollBinding
import com.example.shoppinglist.ui.NewRollAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@Suppress("UNUSED_EXPRESSION")
class AddRollFragment : Fragment(R.layout.fragment_purchase_roll) {
    private lateinit var binding: FragmentPurchaseRollBinding
    private val adapter = NewRollAdapter()
    private val navArgs: AddRollFragmentArgs by navArgs()
    private lateinit var viewModel: RollViewModel
    private lateinit var purchaseName: String

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPurchaseRollBinding.bind(view)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application),
        ).get(RollViewModel::class.java)

        initObservers()

        binding.recyclerView.adapter = adapter

        val id = navArgs.id
        lifecycleScope.launchWhenResumed {
            viewModel.getAllRollsId(id)
        }

        binding.apply {

            lifecycleScope.launchWhenResumed {
                viewModel.nameOfToolbar(id)
                viewModel.nameOfPurchase(id)
            }


            tilField.setEndIconOnClickListener {
                lifecycleScope.launchWhenResumed {
                    if (etName.text.toString().isNotEmpty()) {
                        val roll = Roll(
                            name = etName.text.toString(),
                            topic_id = id,
                            done = false,
                            purchaseName = purchaseName
                        )
                        viewModel.addRoll(roll)
                        etName.text?.clear()
                        etName.clearFocus()
                        viewModel.getAllRollsId(id)

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
                    viewModel.updateRoll(roll)

                    viewModel.getAllRollsId(id)
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
                            viewModel.getAllRollsId(it.topic_id)

                            if (roll.name != it.name) {
                                Snackbar.make(v, "Ozgerdi", Snackbar.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
                R.id.item2 -> {
                    lifecycleScope.launchWhenResumed {
                        viewModel.deleteRoll(roll)
                        viewModel.getAllRollsId(roll.topic_id)
                    }
                }
            }
            true

        }

        popup.show()
    }

    private fun initObservers() {
        viewModel.getAllRollIdFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

        viewModel.nameOfToolbarFlow.onEach {
            binding.tvName.text = it
        }.launchIn(lifecycleScope)

        viewModel.nameOfPurchaseFlow.onEach {
            purchaseName = it
        }.launchIn(lifecycleScope)
    }

}

