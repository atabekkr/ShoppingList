package com.example.shoppinglist.domain.usecases.purchaseusecase

interface DeleteAllRollsByGroupId {

    suspend fun deleteAllRollsByGroupId(id: Int)
}