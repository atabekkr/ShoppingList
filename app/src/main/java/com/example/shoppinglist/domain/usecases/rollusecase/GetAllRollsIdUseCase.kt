package com.example.shoppinglist.domain.usecases.rollusecase

import com.example.shoppinglist.data.Roll
import kotlinx.coroutines.flow.Flow

interface GetAllRollsIdUseCase {

    suspend fun execute(id: Int) : Flow<List<Roll>>
}