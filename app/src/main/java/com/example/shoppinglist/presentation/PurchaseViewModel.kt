package com.example.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.*
import com.example.shoppinglist.domain.usecases.purchaseusecase.impl.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PurchaseViewModel(
    private val addPurchaseUseCase: AddPurchaseUseCaseImpl,
    private val deletePurchaseUseCase: DeletePurchaseUseCaseImpl,
    private val getAllPurchasesUseCase: GetAllPurchasesUseCaseImpl,
    private val updatePurchaseUseCase: UpdatePurchaseUseCaseImpl,
    private val deleteAllRollsByGroupId: DeleteAllRollsByGroupIdImpl
) : ViewModel() {

    val getAllPurchaseFlow = MutableSharedFlow<List<Purchase>>()

    val messageFlow = MutableSharedFlow<String>()
    val activeUsersFlow = MutableSharedFlow<List<User>>()

    suspend fun getAllPurchases() {
        getAllPurchasesUseCase.getAllPurchase().onEach {
            getAllPurchaseFlow.emit(it)
        }.launchIn(viewModelScope)
    }

    suspend fun addPurchase(purchase: Purchase) {
        addPurchaseUseCase.addPurchase(purchase)
    }

    suspend fun updatePurchase(purchase: Purchase) {
        updatePurchaseUseCase.updatePurchase(purchase)
    }

    suspend fun deletePurchase(purchase: Purchase) {
        deletePurchaseUseCase.deletePurchase(purchase)
        deleteAllRollsByGroupId.deleteAllRollsByGroupId(id = purchase.id)
    }
}