package com.example.shoppinglist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RollDao {
    @Query("Select * From rolls")
    fun getAllRoll(): List<Roll>

    @Insert
    fun addRoll(roll: Roll)

    @Delete
    fun deleteRoll(roll: Roll)

    @Query("Select * From rolls Where topic_id=:topicId")
    fun getRoll(topicId: Int): MutableList<Roll>
}