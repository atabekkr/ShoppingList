package com.example.shoppinglist.data.repositories

import com.example.shoppinglist.data.PurchaseDao
import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.data.RollDao
import com.example.shoppinglist.data.models.ResultData
import com.example.shoppinglist.domain.AllItemRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AllItemRepositoryImpl(private val dao: RollDao) : AllItemRepository {


    override suspend fun getAllRolls() = flow {
        emit((dao.getAllRoll()))
    }
}