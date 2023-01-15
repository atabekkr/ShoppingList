package com.example.shoppinglist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Purchase::class, Roll::class], version = 4, exportSchema = false)
abstract class PurchaseDatabase: RoomDatabase() {
    companion object {
        private var instance: PurchaseDatabase? = null

        fun getInstance(context: Context): PurchaseDatabase {
            instance?.let {
                return it
            }

            val db = Room.databaseBuilder(context, PurchaseDatabase::class.java, "purchases.db")
                .allowMainThreadQueries()
                .build()

            instance = db
            return db
        }
    }

    abstract fun getPurchaseDao(): PurchaseDao

    abstract fun getRollDao(): RollDao
}