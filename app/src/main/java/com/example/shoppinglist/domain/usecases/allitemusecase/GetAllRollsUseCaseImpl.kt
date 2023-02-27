package com.example.shoppinglist.domain.usecases.allitemusecase

import android.util.Log
import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.data.models.ResultData
import com.example.shoppinglist.domain.AllItemRepository
import kotlinx.coroutines.flow.*

class GetAllRollsUseCaseImpl(
    private val allItemRepository: AllItemRepository)
    : GetAllRollsUseCase {

    override suspend fun getAllRolls() = flow {
        allItemRepository.getAllRolls().collect {
            Log.d("getAll", "$it")
            emit(it)
        }
    }


}