package com.example.shoppinglist.domain.usecases.allitemusecase

import com.example.shoppinglist.data.Roll
import kotlinx.coroutines.flow.Flow

interface GetAllRollsUseCase {

    suspend fun getAllRolls() : Flow<List<Roll>>


}