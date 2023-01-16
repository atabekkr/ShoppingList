package com.example.shoppinglist.ui.all

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.shoppinglist.R
import com.example.shoppinglist.data.Purchase
import com.example.shoppinglist.data.PurchaseDao
import com.example.shoppinglist.data.PurchaseDatabase
import com.example.shoppinglist.databinding.DialogPurchaseEditBinding

class EditPurchaseDialog(id: Int, var name: String): DialogFragment(R.layout.dialog_purchase_edit) {
    private lateinit var binding: DialogPurchaseEditBinding
    private lateinit var db: PurchaseDatabase
    private lateinit var dao: PurchaseDao

    private val select = id

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogPurchaseEditBinding.bind(view)

        db = PurchaseDatabase.getInstance(requireContext())
        dao = db.getPurchaseDao()


        binding.apply {
            etName.setText(name)

            btnSave.setOnClickListener {
                val name = etName.text.toString()

                if (name.isNotEmpty()) {
                    val purchase = Purchase(
                        id = select,
                        name = name
                    )
                    dao.updatePurchase(purchase)
                    onEditSuccess.invoke()
                    dismiss()
                } else {
                    Toast.makeText(requireContext(), "Toltir", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private var onEditSuccess: () -> Unit = {}
    fun setOnEditSuccessListener(onEditSuccess: () -> Unit = {}) {
        this.onEditSuccess = onEditSuccess
    }
}