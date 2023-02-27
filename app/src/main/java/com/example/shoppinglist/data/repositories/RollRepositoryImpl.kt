package com.example.shoppinglist.data.repositories

import androidx.compose.animation.core.estimateAnimationDurationMillis
import com.example.shoppinglist.data.PurchaseDao
import com.example.shoppinglist.data.Roll
import com.example.shoppinglist.data.RollDao
import com.example.shoppinglist.domain.RollRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RollRepositoryImpl(
    private val daoRoll: RollDao,
    private val daoPurchase: PurchaseDao
) : RollRepository {


    override suspend fun getAllRollsId(id: Int) = flow {
        val list = daoRoll.getRoll(id)

        list.sortBy {
            it.id
        }
        list.sortBy {
            it.done
        }

        emit(list)
    }

    override suspend fun deleteRoll(roll: Roll) {
        daoRoll.deleteRoll(roll)
    }

    override suspend fun updateRoll(roll: Roll) {
        daoRoll.updateRoll(roll)
    }

    override suspend fun addRoll(roll: Roll) {
        daoRoll.addRoll(roll)
    }

    override suspend fun deleteAllRollsByGroupId(id:Int) {
        daoRoll.getRoll(id).forEach {
            daoRoll.deleteRoll(it)
        }
    }

    override suspend fun getNameOfPurchase(id: Int) = flow {
         emit(daoPurchase.getPurchase(id).name)
    }
}