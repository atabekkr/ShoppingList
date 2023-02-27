package com.example.shoppinglist.domain.usecases.purchaseusecase.impl

import com.example.shoppinglist.data.Purchase
import com.example.shoppinglist.domain.PurchaseRepository
import com.example.shoppinglist.domain.usecases.purchaseusecase.UpdatePurchaseUseCase

class UpdatePurchaseUseCaseImpl(private val purchaseRepository: PurchaseRepository)
    : UpdatePurchaseUseCase {

    override suspend fun updatePurchase(purchase: Purchase) =
        purchaseRepository.updatePurchase(purchase)

}