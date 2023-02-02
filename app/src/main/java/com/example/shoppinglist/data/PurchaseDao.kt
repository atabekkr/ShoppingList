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
    fun getAllLists(): List<Purchase>

    @Insert(entity = Purchase::class)
    fun addPurchase(purchase: Purchase)

    @Delete
    fun deletePurchase(purchase: Purchase)

    @Update(entity = Purchase::class, onConflict = OnConflictStrategy.REPLACE)
    fun updatePurchase(purchase: Purchase)

    @Query("SELECT * FROM purchases WHERE id=:topicId")
    fun getPurchase(topicId: Int): Purchase
}