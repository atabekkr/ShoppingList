package com.example.shoppinglist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.*
import kotlinx.coroutines.flow.MutableSharedFlow

class MainViewModel(application: Application): AndroidViewModel(application) {
    private var daoPurchase: PurchaseDao
    private var daoRoll: RollDao
    private var db: PurchaseDatabase

    val getAllPurchaseFlow = MutableSharedFlow<List<Purchase>>()
    val getAllRollIdFlow = MutableSharedFlow<List<Roll>>()
    val getAllRollFlow = MutableSharedFlow<List<Roll>>()
    val nameOfToolbarFlow = MutableSharedFlow<String>()
    val nameOfPurchaseFlow = MutableSharedFlow<String>()

    init {
        db = PurchaseDatabase.getInstance(application)
        daoPurchase = db.getPurchaseDao()
        daoRoll = db.getRollDao()
    }

    suspend fun getAllElements() {
        val list = daoPurchase.getAllLists()
        getAllPurchaseFlow.emit(list)
    }

    suspend fun addPurchase(purchase: Purchase) {
        daoPurchase.addPurchase(purchase)
    }

    suspend fun getAllRollsId(id: Int) {
        val list = daoRoll.getRoll(id)

        list.sortBy {
            it.id
        }
        list.sortBy {
            it.done
        }

        getAllRollIdFlow.emit(list)
    }

    suspend fun getAllRolls() {
        val list = daoRoll.getAllRoll()

        getAllRollFlow.emit(list)
    }

    suspend fun deleteRoll(roll: Roll) {
        daoRoll.deleteRoll(roll)
    }

    suspend fun updateRoll(roll: Roll) {
        daoRoll.updateRoll(roll)
    }

    suspend fun updatePurchase(purchase: Purchase) {
        daoPurchase.updatePurchase(purchase)
    }

    suspend fun addRoll(roll: Roll) {
        daoRoll.addRoll(roll)
    }

    suspend fun nameOfToolbar(id: Int) {
        val name = daoPurchase.getPurchase(id).name
        nameOfToolbarFlow.emit(name)
    }

    suspend fun nameOfPurchase(id: Int) {
        val name = daoPurchase.getPurchase(id).name
        nameOfPurchaseFlow.emit(name)
    }

    suspend fun deletePurchase(purchase: Purchase) {
        daoPurchase.deletePurchase(purchase)
    }

    suspend fun deleteAllRoll(id: Int) {
        daoRoll.getRoll(id).forEach {
            daoRoll.deleteRoll(it)
        }
    }
}