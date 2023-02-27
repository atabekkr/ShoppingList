package com.example.shoppinglist.domain.usecases.purchaseusecase.impl

import com.example.shoppinglist.domain.RollRepository
import com.example.shoppinglist.domain.usecases.purchaseusecase.DeleteAllRollsByGroupId

class DeleteAllRollsByGroupIdImpl(private val rollRepository: RollRepository) :
    DeleteAllRollsByGroupId {

    override suspend fun deleteAllRollsByGroupId(id: Int) =
        (rollRepository.deleteAllRollsByGroupId(id))

}