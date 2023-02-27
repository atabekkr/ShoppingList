package com.example.shoppinglist.domain

import com.example.shoppinglist.data.Roll
import kotlinx.coroutines.flow.Flow

interface RollRepository {

    suspend fun getAllRollsId(id: Int) : Flow<List<Roll>>

    suspend fun deleteRoll(roll: Roll)

    suspend fun updateRoll(roll: Roll)

    suspend fun addRoll(roll: Roll)

    suspend fun deleteAllRollsByGroupId(id: Int)

    suspend fun getNameOfPurchase(id: Int): Flow<String>
}