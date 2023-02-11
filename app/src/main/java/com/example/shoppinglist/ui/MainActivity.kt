package com.example.shoppinglist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.i
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ActivityMainBinding
import com.example.shoppinglist.ui.all.AllItemFragment
import com.example.shoppinglist.ui.all.AllPurchasesFragment

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = this.supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.findNavController()

        binding.bnvMain.setupWithNavController(navController)

        /*binding.bnvMain.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.allPurchasesFragment ->{
                    binding.bnvMain.selectedItemId = R.id.allPurchasesFragment
                }
                R.id.allItemFragment -> {
                    binding.bnvMain.selectedItemId = R.id.allItemFragment
                }
            }
            true
        }*/
    }
}