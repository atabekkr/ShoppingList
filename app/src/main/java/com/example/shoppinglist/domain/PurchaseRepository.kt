package com.example.shoppinglist.domain

import com.example.shoppinglist.data.Purchase
import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.data.models.ResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface PurchaseRepository {

    suspend fun nameOfToolbar(id: Int) : Flow<String>

    suspend fun nameOfPurchase(id: Int): Flow<String>

    suspend fun getAllPurchases() : Flow<List<Purchase>>

    suspend fun addPurchase(purchase: Purchase)

    suspend fun updatePurchase(purchase: Purchase)

    suspend fun deletePurchase(purchase: Purchase)
}