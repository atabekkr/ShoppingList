package com.example.shoppinglist.domain.usecases.purchaseusecase.impl

import com.example.shoppinglist.data.Purchase
import com.example.shoppinglist.domain.PurchaseRepository
import com.example.shoppinglist.domain.usecases.purchaseusecase.AddPurchaseUseCase

class AddPurchaseUseCaseImpl(private val purchaseRepository: PurchaseRepository):
    AddPurchaseUseCase {

    override suspend fun addPurchase(purchase: Purchase) = purchaseRepository.addPurchase(purchase)
}