package com.example.shoppinglist.ui.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.DialogPurchaseAddBinding
import com.example.shoppinglist.databinding.FragmentPurchaseAddBinding

class AddPurchaseFragment: Fragment(R.layout.fragment_purchase_add) {

    private lateinit var binding: FragmentPurchaseAddBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPurchaseAddBinding.bind(view)
    }
}