package com.example.shoppinglist.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.*
import com.example.shoppinglist.data.models.ResultData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RollViewModel(application: Application): AndroidViewModel(application) {
    private val daoPurchase: PurchaseDao
    private val daoRoll: RollDao
    private val db: PurchaseDatabase
    private val repo: MainRepository

    val getAllRollIdFlow = MutableSharedFlow<List<Roll>>()
    val getAllRollFlow = MutableSharedFlow<List<Roll>>()
    val nameOfToolbarFlow = MutableSharedFlow<String>()
    val nameOfPurchaseFlow = MutableSharedFlow<String>()
    val messageFlow = MutableSharedFlow<String>()
    val errorFlow = MutableSharedFlow<Throwable>()

    init {
        db = PurchaseDatabase.getInstance(application)
        daoPurchase = db.getPurchaseDao()
        daoRoll = db.getRollDao()
        repo = MainRepository(daoRoll, daoPurchase)
    }

    suspend fun getAllRollsId(id: Int) {

        repo.getAllRollsId(id).onEach {
            getAllRollIdFlow.emit(it)
        }.launchIn(viewModelScope)
    }

    suspend fun getAllRolls() {

        repo.getAllRolls().onEach {
            when (it) {
                is ResultData.Success -> {
                    getAllRollFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    messageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    errorFlow.emit(it.error)
                }
            }

        }.launchIn(viewModelScope)
    }

    suspend fun deleteRoll(roll: Roll) {
        repo.deleteRoll(roll)
    }

    suspend fun updateRoll(roll: Roll) {
        repo.updateRoll(roll)
    }

    suspend fun addRoll(roll: Roll) {
        repo.addRoll(roll)
    }

    suspend fun nameOfToolbar(id: Int) {
        repo.nameOfToolbar(id).onEach {
            nameOfToolbarFlow.emit(it)
        }.launchIn(viewModelScope)
    }

    suspend fun nameOfPurchase(id: Int) {
        repo.nameOfPurchase(id).onEach {
            nameOfPurchaseFlow.emit(it)
        }.launchIn(viewModelScope)
    }
}