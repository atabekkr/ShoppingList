package com.example.shoppinglist.domain.usecases.purchaseusecase.impl

import com.example.shoppinglist.domain.PurchaseRepository
import com.example.shoppinglist.domain.usecases.purchaseusecase.GetAllPurchaseUseCase
import kotlinx.coroutines.flow.flow

class GetAllPurchasesUseCaseImpl(private val purchaseRepository: PurchaseRepository):
    GetAllPurchaseUseCase {


    override suspend fun getAllPurchase() = flow {
        purchaseRepository.getAllPurchases().collect() {
             emit(it)
        }
    }
}