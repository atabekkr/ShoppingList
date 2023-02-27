package com.example.shoppinglist.domain.usecases.rollusecase

import kotlinx.coroutines.flow.Flow

interface NameOfToolbarUseCase {

    suspend fun nameOfToolbar(id: Int) : Flow<String>
}