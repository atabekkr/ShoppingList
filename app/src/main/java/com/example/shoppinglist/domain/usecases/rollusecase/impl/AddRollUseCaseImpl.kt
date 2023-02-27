package com.example.shoppinglist.domain.usecases.rollusecase.impl

import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.domain.RollRepository
import com.example.shoppinglist.domain.usecases.rollusecase.AddRollUseCase

class AddRollUseCaseImpl(private val rollRepository: RollRepository)
    : AddRollUseCase {

    override suspend fun addRoll(roll: Roll) =
        rollRepository.addRoll(roll)
}