package com.example.shoppinglist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ActivityMainBinding
import com.example.shoppinglist.ui.all.AllItemFragment
import com.example.shoppinglist.ui.all.AllPurchasesFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bnvMain.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.item_sale -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, AllItemFragment())
                        .commit()
                }
            }
            true
        }
    }
}