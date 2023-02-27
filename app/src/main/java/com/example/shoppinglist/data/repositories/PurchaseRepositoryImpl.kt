package com.example.shoppinglist.data.repositories

import com.example.shoppinglist.data.Purchase
import com.example.shoppinglist.data.PurchaseDao
import com.example.shoppinglist.data.models.ResultData
import com.example.shoppinglist.domain.PurchaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class PurchaseRepositoryImpl(
    private val daoPurchase: PurchaseDao
) : PurchaseRepository {

    override suspend fun nameOfToolbar(id: Int) = flow {
            emit(daoPurchase.getPurchase(id).name)
    }

    override suspend fun nameOfPurchase(id: Int) = flow {
        emit(daoPurchase.getPurchase(id).name)
    }

    override suspend fun getAllPurchases() = flow {
         emit((daoPurchase.getAllLists()))
    }

    override suspend fun addPurchase(purchase: Purchase) {
        daoPurchase.addPurchase(purchase)
    }

    override suspend fun updatePurchase(purchase: Purchase) {
        daoPurchase.updatePurchase(purchase)
    }

    override suspend fun deletePurchase(purchase: Purchase) {
        daoPurchase.deletePurchase(purchase)
    }
}