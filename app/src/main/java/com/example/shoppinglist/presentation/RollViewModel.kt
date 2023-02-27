package com.example.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.*
import com.example.shoppinglist.domain.usecases.rollusecase.impl.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RollViewModel(
    private val addRollUseCase: AddRollUseCaseImpl,
    private val deleteRollUseCase: DeleteRollUseCaseImpl,
    private val getAllRolsIdUseCase: GetAllRolsIdUseCaseImpl,
    private val updateRollUseCase: UpdateRollUseCaseImpl,
    private val nameOfToolbarUseCase: NameOfToolbarUseCaseImpl,
    private val nameOfPurchaseUseCase: NameOfPurchaseUseCaseImpl
): ViewModel() {

    val getAllRollIdFlow = MutableSharedFlow<List<Roll>>()
    val nameOfToolbarFlow = MutableSharedFlow<String>()
    val nameOfPurchaseFlow = MutableSharedFlow<String>()

    suspend fun getAllRollsId(id: Int) {

        getAllRolsIdUseCase.execute(id).onEach {
            getAllRollIdFlow.emit(it)
        }.launchIn(viewModelScope)
    }

    suspend fun deleteRoll(roll: Roll) {
        deleteRollUseCase.deleteRoll(roll)
    }

    suspend fun updateRoll(roll: Roll) {
        updateRollUseCase.updateRoll(roll)
    }

    suspend fun addRoll(roll: Roll) {
        addRollUseCase.addRoll(roll)
    }

    suspend fun nameOfToolbar(id: Int) {
        nameOfToolbarUseCase.nameOfToolbar(id).onEach {
           nameOfToolbarFlow.emit(it)
        }.launchIn(viewModelScope)
    }

    suspend fun nameOfPurchase(id: Int) {
        nameOfPurchaseUseCase.nameOfPurchase(id).onEach {
            nameOfPurchaseFlow.emit(it)
        }.launchIn(viewModelScope)
    }
}