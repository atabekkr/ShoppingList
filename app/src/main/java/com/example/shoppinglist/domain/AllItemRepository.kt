package com.example.shoppinglist.domain

import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface AllItemRepository {

    suspend fun getAllRolls(): Flow<List<Roll>>
}