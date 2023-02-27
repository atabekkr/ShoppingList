package com.example.shoppinglist.domain.usecases.rollusecase.impl

import com.example.shoppinglist.domain.RollRepository
import com.example.shoppinglist.domain.usecases.rollusecase.GetAllRollsIdUseCase
import kotlinx.coroutines.flow.flow

class GetAllRolsIdUseCaseImpl(private val rollRepository: RollRepository)
    : GetAllRollsIdUseCase {

    override suspend fun execute(id: Int) = flow {
        rollRepository.getAllRollsId(id).collect() {
            emit(it)
        }
    }
}
