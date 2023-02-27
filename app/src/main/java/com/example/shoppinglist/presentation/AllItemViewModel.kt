package com.example.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.data.models.ResultData
import com.example.shoppinglist.domain.usecases.allitemusecase.GetAllRollsUseCaseImpl
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AllItemViewModel(
    private val getAllRollsUseCase: GetAllRollsUseCaseImpl,
) : ViewModel() {

    val getAllRollFlow = MutableSharedFlow<List<Roll>>()

    suspend fun getAllRolls() {
        getAllRollsUseCase.getAllRolls().onEach {
            getAllRollFlow.emit(it)
        }.launchIn(viewModelScope)
    }
}