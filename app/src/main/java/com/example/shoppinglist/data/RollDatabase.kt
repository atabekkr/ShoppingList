package com.example.shoppinglist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Roll::class], version = 1)
abstract class RollDatabase: RoomDatabase() {
    companion object {
        private var instance: RollDatabase? = null

        fun getInstance(context: Context): RollDatabase {
            instance?.let {
                return it
            }

            val db = Room.databaseBuilder(context, RollDatabase::class.java, "rolls.db")
                .allowMainThreadQueries()
                .build()

            instance = db
            return db
        }
    }

    abstract fun getRollDao(): RollDao
}