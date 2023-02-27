package com.example.shoppinglist.domain.usecases.rollusecase.impl

import android.util.Log
import com.example.shoppinglist.domain.RollRepository
import com.example.shoppinglist.domain.usecases.rollusecase.NameOfPurchaseUseCase
import kotlinx.coroutines.flow.flow

class NameOfPurchaseUseCaseImpl(private val rollRepository: RollRepository)
    : NameOfPurchaseUseCase {

    override suspend fun nameOfPurchase(id: Int) = flow {
        rollRepository.getNameOfPurchase(id).collect() {
            Log.w("purchaseName", it)
            emit(it)
        }
    }

}