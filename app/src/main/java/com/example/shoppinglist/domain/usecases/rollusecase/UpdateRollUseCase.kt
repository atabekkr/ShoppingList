package com.example.shoppinglist.domain.usecases.rollusecase

import com.example.shoppinglist.data.Roll

interface UpdateRollUseCase {

    suspend fun updateRoll(roll: Roll)
}