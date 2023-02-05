package com.example.shoppinglist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RollDao {
    @Query("Select * From rolls")
    suspend fun getAllRoll(): List<Roll>

    @Insert
    suspend fun addRoll(roll: Roll)

    @Delete
    suspend fun deleteRoll(roll: Roll)

    @Query("Select * From rolls Where topic_id=:topicId")
    suspend fun getRoll(topicId: Int): MutableList<Roll>

    @Update
    suspend fun updateRoll(roll: Roll)
}