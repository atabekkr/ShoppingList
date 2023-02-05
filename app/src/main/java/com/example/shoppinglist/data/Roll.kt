package com.example.shoppinglist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rolls")
data class Roll(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val topic_id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo var done: Boolean,
    @ColumnInfo(name = "purchase_name") val purchaseName: String
)
