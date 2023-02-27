package com.example.shoppinglist.domain.usecases.rollusecase

import com.example.shoppinglist.data.Roll

interface DeleteRollUseCase {


    suspend fun deleteRoll(roll: Roll)



}