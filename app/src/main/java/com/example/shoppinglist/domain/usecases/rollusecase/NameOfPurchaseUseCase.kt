package com.example.shoppinglist.domain.usecases.rollusecase

import kotlinx.coroutines.flow.Flow

interface NameOfPurchaseUseCase {

    suspend fun nameOfPurchase(id: Int) : Flow<String>
}