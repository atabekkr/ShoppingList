package com.example.shoppinglist.di

import android.app.Application
import androidx.room.Room
import com.example.shoppinglist.data.PurchaseDao
import com.example.shoppinglist.data.PurchaseDatabase
import com.example.shoppinglist.data.RollDao
import com.example.shoppinglist.data.repositories.AllItemRepositoryImpl
import com.example.shoppinglist.data.repositories.PurchaseRepositoryImpl
import com.example.shoppinglist.data.repositories.RollRepositoryImpl
import com.example.shoppinglist.domain.AllItemRepository
import com.example.shoppinglist.domain.PurchaseRepository
import com.example.shoppinglist.domain.RollRepository
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {

    single<PurchaseRepository> {
        PurchaseRepositoryImpl(daoPurchase = get())
    }

    single<RollRepository> {
        RollRepositoryImpl(daoRoll = get(), daoPurchase = get())
    }

    single<AllItemRepository> {
        AllItemRepositoryImpl(dao = get())
    }

    fun provideDataBase(application: Application): PurchaseDatabase {
        return Room.databaseBuilder(application, PurchaseDatabase::class.java, "PurchaseDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(dataBase: PurchaseDatabase): RollDao {
        return dataBase.getRollDao()
    }

    fun providePurchaseDao(database: PurchaseDatabase): PurchaseDao {
        return database.getPurchaseDao()
    }
    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
    single { providePurchaseDao(get()) }

}