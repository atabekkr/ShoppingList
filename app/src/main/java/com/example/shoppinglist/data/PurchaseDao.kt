package com.example.shoppinglist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PurchaseDao {
    @Query("SELECT * FROM purchases")
    suspend fun getAllLists(): List<Purchase>

    @Insert(entity = Purchase::class)
    suspend fun addPurchase(purchase: Purchase)

    @Delete
    suspend fun deletePurchase(purchase: Purchase)

    @Update(entity = Purchase::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePurchase(purchase: Purchase)

    @Query("SELECT * FROM purchases WHERE id=:topicId")
    suspend fun getPurchase(topicId: Int): Purchase
}