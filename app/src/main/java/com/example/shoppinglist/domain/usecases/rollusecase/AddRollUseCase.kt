package com.example.shoppinglist.domain.usecases.rollusecase

import com.example.shoppinglist.data.Roll

interface AddRollUseCase {

    suspend fun addRoll(roll: Roll)
}