package com.example.shoppinglist.domain.usecases.rollusecase.impl

import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.domain.RollRepository
import com.example.shoppinglist.domain.usecases.rollusecase.DeleteRollUseCase

class DeleteRollUseCaseImpl(private val rollRepository: RollRepository) : DeleteRollUseCase {

    override suspend fun deleteRoll(roll: Roll) = rollRepository.deleteRoll(roll)

}