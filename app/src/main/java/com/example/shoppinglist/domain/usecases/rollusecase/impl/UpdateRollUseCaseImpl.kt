package com.example.shoppinglist.domain.usecases.rollusecase.impl

import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.domain.RollRepository
import com.example.shoppinglist.domain.usecases.rollusecase.UpdateRollUseCase

class UpdateRollUseCaseImpl(private val rollRepository: RollRepository)
    : UpdateRollUseCase {

    override suspend fun updateRoll(roll: Roll) =
        rollRepository.updateRoll(roll)

}