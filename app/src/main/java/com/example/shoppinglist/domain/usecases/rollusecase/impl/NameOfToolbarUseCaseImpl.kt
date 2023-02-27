package com.example.shoppinglist.domain.usecases.rollusecase.impl

import com.example.shoppinglist.domain.PurchaseRepository
import com.example.shoppinglist.domain.usecases.rollusecase.NameOfToolbarUseCase
import kotlinx.coroutines.flow.flow

class NameOfToolbarUseCaseImpl(private val purchaseRepository: PurchaseRepository)
    : NameOfToolbarUseCase {

    override suspend fun nameOfToolbar(id: Int) = flow {
        purchaseRepository.nameOfToolbar(id).collect() {
            emit(it)
        }
    }


}