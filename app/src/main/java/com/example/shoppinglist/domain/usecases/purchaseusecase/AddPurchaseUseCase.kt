package com.example.shoppinglist.domain.usecases.purchaseusecase

import com.example.shoppinglist.data.Purchase

interface AddPurchaseUseCase {

    suspend fun addPurchase(purchase: Purchase)
}