package com.example.shoppinglist.ui

import android.view.inputmethod.InputBinding
import com.example.shoppinglist.data.Purchase
import com.example.shoppinglist.data.PurchaseDao
import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.data.RollDao
import com.example.shoppinglist.data.models.ResultData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MainRepository(private val daoRoll: RollDao, private val daoPurchase: PurchaseDao) {

    suspend fun getAllRolls() = flow {
        val list = daoRoll.getAllRoll()

        if (list.isNotEmpty()) {
            emit(ResultData.Success(list))
        } else {
            emit(ResultData.Message("Pustoy"))
        }
    }.catch {
        emit(ResultData.Error(it))
    }

    suspend fun getAllRollsId(id: Int) = flow {
        val list = daoRoll.getRoll(id)

        list.sortBy {
            it.id
        }
        list.sortBy {
            it.done
        }

        emit(list)
    }

    suspend fun deleteRoll(roll: Roll) {
        daoRoll.deleteRoll(roll)
    }

    suspend fun updateRoll(roll: Roll) {
        daoRoll.updateRoll(roll)
    }

    suspend fun addRoll(roll: Roll) {
        daoRoll.addRoll(roll)
    }

    suspend fun nameOfToolbar(id: Int) = flow {
        val name = daoPurchase.getPurchase(id).name
        emit(name)
    }

    suspend fun nameOfPurchase(id: Int) = flow {
        val name = daoPurchase.getPurchase(id).name
        emit(name)
    }

    suspend fun getAllPurchases() = flow {
        val list = daoPurchase.getAllLists()
        emit(list)
    }

    suspend fun addPurchase(purchase: Purchase) {
        daoPurchase.addPurchase(purchase)
    }

    suspend fun updatePurchase(purchase: Purchase) {
        daoPurchase.updatePurchase(purchase)
    }

    suspend fun deletePurchase(purchase: Purchase) {
        daoPurchase.deletePurchase(purchase)
        daoRoll.getRoll(purchase.id).forEach {
            daoRoll.deleteRoll(it)
        }
    }
}