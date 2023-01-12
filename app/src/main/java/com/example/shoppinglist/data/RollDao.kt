package com.example.shoppinglist.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RollDao {
    @Query("Select * From rolls")
    fun getAllRoll(): List<Roll>

    @Insert
    fun addRoll(roll: Roll)
}