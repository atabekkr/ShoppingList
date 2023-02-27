package com.example.shoppinglist.domain.usecases.purchaseusecase

import com.example.shoppinglist.data.Purchase

interface UpdatePurchaseUseCase {

    suspend fun updatePurchase(purchase: Purchase)
}