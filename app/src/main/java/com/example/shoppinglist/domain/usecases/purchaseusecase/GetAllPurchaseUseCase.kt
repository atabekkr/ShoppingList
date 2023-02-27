package com.example.shoppinglist.domain.usecases.purchaseusecase

import com.example.shoppinglist.data.Purchase
import com.example.shoppinglist.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface GetAllPurchaseUseCase {

    suspend fun getAllPurchase(): Flow<List<Purchase>>
}