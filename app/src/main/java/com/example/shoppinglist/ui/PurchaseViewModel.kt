package com.example.shoppinglist.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.Purchase
import com.example.shoppinglist.data.PurchaseDao
import com.example.shoppinglist.data.PurchaseDatabase
import com.example.shoppinglist.data.RollDao
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PurchaseViewModel(application: Application): AndroidViewModel(application) {
    private val db: PurchaseDatabase
    private val daoPurchase: PurchaseDao
    private val daoRoll: RollDao
    val repo: MainRepository

    val getAllPurchaseFlow = MutableSharedFlow<List<Purchase>>()

    init {
        db = PurchaseDatabase.getInstance(application)
        daoPurchase = db.getPurchaseDao()
        daoRoll = db.getRollDao()
        repo = MainRepository(daoRoll, daoPurchase)
    }

    suspend fun getAllElements() {
        repo.getAllPurchases().onEach {
            getAllPurchaseFlow.emit(it)
        }.launchIn(viewModelScope)
    }

    suspend fun addPurchase(purchase: Purchase) {
        repo.addPurchase(purchase)
    }

    suspend fun updatePurchase(purchase: Purchase) {
        repo.updatePurchase(purchase)
    }

    suspend fun deletePurchase(purchase: Purchase) {
        repo.deletePurchase(purchase)
    }
}