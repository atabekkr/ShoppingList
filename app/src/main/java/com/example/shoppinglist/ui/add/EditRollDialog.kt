package com.example.shoppinglist.ui.add

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.shoppinglist.presentation.RollViewModel
import com.example.shoppinglist.R
import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.databinding.DialogRollEditBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditRollDialog(id: Int, private val topicId: Int, private val name: String, private val done: Boolean, private val purchaseName: String): DialogFragment(R.layout.dialog_roll_edit) {
    private lateinit var binding: DialogRollEditBinding
    private val select = id
    private val viewModel by viewModel<RollViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogRollEditBinding.bind(view)

        binding.apply {
            etName.setText(name)

            btnSave.setOnClickListener {

                lifecycleScope.launchWhenResumed {
                    val name = etName.text.toString()

                    if (name.isNotEmpty()) {
                        val roll = Roll(
                            select,
                            topicId,
                            name,
                            done,
                            purchaseName
                        )
                        viewModel.updateRoll(roll)
                        onEditRoll.invoke(roll)
                        dismiss()
                    } else {
                        Toast.makeText(requireContext(), "Toltir", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }

    private var onEditRoll: (roll: Roll) -> Unit = {}
    fun setOnEditRollListener(onEditRoll: (roll: Roll) -> Unit) {
        this.onEditRoll = onEditRoll
    }

}