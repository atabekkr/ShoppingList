package com.example.shoppinglist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ActivityMainBinding
import com.example.shoppinglist.ui.all.AllPurchasesFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, AllPurchasesFragment())
            .commit()

      /*  binding.apply {
            bnvMain.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.item_main -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, AllPurchasesFragment())
                            .commit()
                    }
                    R.id.item_sale -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, AddPurchaseFragment())
                            .commit()
                    }
                }
                true
            }
        }*/
    }
}