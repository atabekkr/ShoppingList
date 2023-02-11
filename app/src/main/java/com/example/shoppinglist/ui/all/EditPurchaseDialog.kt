package com.example.shoppinglist.ui.all

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.shoppinglist.R
import com.example.shoppinglist.data.Purchase
import com.example.shoppinglist.databinding.DialogPurchaseEditBinding
import com.example.shoppinglist.ui.PurchaseViewModel
import java.text.SimpleDateFormat
import java.util.*

class EditPurchaseDialog(id: Int, var name: String, private var date: String): DialogFragment(R.layout.dialog_purchase_edit) {
    private lateinit var binding: DialogPurchaseEditBinding
    private lateinit var viewModel: PurchaseViewModel

    private val select = id

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogPurchaseEditBinding.bind(view)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        ).get(PurchaseViewModel::class.java)
        val c = Calendar.getInstance()

        binding.apply {
            etName.setText(name)
            tvDate.text = date

            btnSave.setOnClickListener {
                val name = etName.text.toString()

                if (name.isNotEmpty()) {
                    val purchase = Purchase(
                        id = select,
                        name = name,
                        date = date
                    )
                    lifecycleScope.launchWhenResumed {
                        viewModel.updatePurchase(purchase)
                        onEditSuccess.invoke()
                        dismiss()
                    }
                } else {
                    Toast.makeText(requireContext(), "Toltir", Toast.LENGTH_SHORT).show()
                }
            }

            val dpd = DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
            c.set(Calendar.YEAR, year)
            c.set(Calendar.MONTH, month)
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable(c)
        }

            tvDate.setOnClickListener {
                DatePickerDialog(requireContext(), dpd, c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
            }

        }
    }

    private fun updateLable(c: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        date = sdf.format(c.time)
    }

    private var onEditSuccess: () -> Unit = {}
    fun setOnEditSuccessListener(onEditSuccess: () -> Unit = {}) {
        this.onEditSuccess = onEditSuccess
    }
}