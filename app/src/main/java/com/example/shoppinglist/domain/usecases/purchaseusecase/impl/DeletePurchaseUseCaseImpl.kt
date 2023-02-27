package com.example.shoppinglist.domain.usecases.purchaseusecase.impl

import com.example.shoppinglist.data.Purchase
import com.example.shoppinglist.domain.PurchaseRepository
import com.example.shoppinglist.domain.usecases.purchaseusecase.DeletePurchaseUseCase

class DeletePurchaseUseCaseImpl(private val purchaseRepository: PurchaseRepository)
    : DeletePurchaseUseCase {

    override suspend fun deletePurchase(purchase: Purchase) =
        purchaseRepository.deletePurchase(purchase)

}