package com.example.shoppinglist.ui.all

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.shoppinglist.R
import com.example.shoppinglist.data.Purchase
import com.example.shoppinglist.data.PurchaseDatabase
import com.example.shoppinglist.databinding.DialogPurchaseAddBinding
import com.example.shoppinglist.presentation.PurchaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddPurchaseDialog(private val lastId: Int): DialogFragment(R.layout.dialog_purchase_add) {
    private lateinit var binding: DialogPurchaseAddBinding
    private lateinit var date: String
    private val viewModel by viewModel<PurchaseViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogPurchaseAddBinding.bind(view)

        val c = Calendar.getInstance()

        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        date = sdf.format(c.time)

        binding.apply {
            tvDate.text = date

            btnAdd.setOnClickListener {
                val name = etName.text.toString()

                if (name.isNotEmpty()) {
                    val purchase = Purchase(
                        id = lastId + 1,
                        name = name,
                        date = date
                    )
                    lifecycleScope.launchWhenResumed {
                        viewModel.addPurchase(purchase)
                        onAddSuccess.invoke(1 + lastId) // purchase.id = 0
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
                DatePickerDialog(requireContext(), dpd, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH)).show()
            }
        }
    }

    private var onAddSuccess: (id: Int) -> Unit = {}
    fun setOnAddSuccessListener(onAddSuccess: (id: Int) -> Unit) {
        this.onAddSuccess = onAddSuccess
    }

    private fun updateLable(c: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        date = sdf.format(c.time)
    }
}


