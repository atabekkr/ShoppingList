package com.example.shoppinglist.ui.all

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.shoppinglist.R
import com.example.shoppinglist.data.Purchase
import com.example.shoppinglist.data.PurchaseDatabase
import com.example.shoppinglist.databinding.DialogPurchaseAddBinding
import com.google.android.material.snackbar.Snackbar

class AddPurchaseDialog: DialogFragment(R.layout.dialog_purchase_add) {
    private lateinit var binding: DialogPurchaseAddBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogPurchaseAddBinding.bind(view)

        val dao = PurchaseDatabase.getInstance(requireContext()).getPurchaseDao()

        binding.apply {
            btnAdd.setOnClickListener {
                val name = etName.text.toString()

                if (name.isNotEmpty()) {
                    val purchase = Purchase(
                        name = name
                    )
                    dao.addPurchase(purchase)
                    onAddSuccess.invoke()
                    dismiss()
                } else {
                    Toast.makeText(requireContext(), "Toltir", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private var onAddSuccess: () -> Unit = {}
    fun setOnAddSuccessListener(onAddSuccess: () -> Unit) {
        this.onAddSuccess = onAddSuccess
    }
}