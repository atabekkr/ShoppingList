package com.example.shoppinglist.ui.add

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.shoppinglist.R
import com.example.shoppinglist.data.PurchaseDatabase
import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.data.RollDao
import com.example.shoppinglist.databinding.DialogPurchaseEditBinding

class EditRollDialog(id: Int, private val topicId: Int, var name: String, private var done: Int): DialogFragment(R.layout.dialog_purchase_edit) {
    private lateinit var binding: DialogPurchaseEditBinding
    private lateinit var db: PurchaseDatabase
    private lateinit var dao: RollDao
    private val select = id

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogPurchaseEditBinding.bind(view)

        db = PurchaseDatabase.getInstance(requireContext())
        dao = db.getRollDao()

        binding.apply {
            etName.setText(name)

            btnSave.setOnClickListener {
                val name = etName.text.toString()

                if (name.isNotEmpty()) {
                    val roll = Roll(
                        select,
                        topicId,
                        name,
                        done
                    )
                    dao.updateRoll(roll)
                    onEditRoll.invoke()
                    dismiss()
                } else {
                    Toast.makeText(requireContext(), "Toltir", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private var onEditRoll: () -> Unit = {}
    fun setOnEditRollListener(onEditRoll: () -> Unit) {
        this.onEditRoll = onEditRoll
    }

}