package com.example.shoppinglist.domain.usecases.purchaseusecase

import com.example.shoppinglist.data.Purchase

interface DeletePurchaseUseCase {

    suspend fun deletePurchase(purchase: Purchase)
}