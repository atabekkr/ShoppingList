package com.example.shoppinglist.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PurchaseDao {
    @Query("SELECT * FROM purchases")
    fun getAllLists(): List<Purchase>

    @Insert(entity = Purchase::class)
    fun addPurchase(purchase: Purchase)
}