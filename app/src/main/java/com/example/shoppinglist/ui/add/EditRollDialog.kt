package com.example.shoppinglist.ui.add

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.shoppinglist.R
import com.example.shoppinglist.data.PurchaseDatabase
import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.data.RollDao
import com.example.shoppinglist.databinding.DialogPurchaseEditBinding
import com.example.shoppinglist.databinding.DialogRollEditBinding

class EditRollDialog(id: Int, private val topicId: Int, private val name: String, private val done: Boolean, private val purchaseName: String): DialogFragment(R.layout.dialog_roll_edit) {
    private lateinit var binding: DialogRollEditBinding
    private lateinit var db: PurchaseDatabase
    private lateinit var dao: RollDao
    private val select = id

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogRollEditBinding.bind(view)

        db = PurchaseDatabase.getInstance(requireContext())
        dao = db.getRollDao()

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
                        dao.updateRoll(roll)
                        println("###################${dao.getRoll(topicId)}")
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