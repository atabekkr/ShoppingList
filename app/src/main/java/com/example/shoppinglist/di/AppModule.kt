package com.example.shoppinglist.di

import com.example.shoppinglist.presentation.AllItemViewModel
import com.example.shoppinglist.presentation.PurchaseViewModel
import com.example.shoppinglist.presentation.RollViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<AllItemViewModel>() {
        AllItemViewModel(getAllRollsUseCase = get())
    }

    viewModel<PurchaseViewModel>() {
        PurchaseViewModel(
            addPurchaseUseCase = get(),
            deletePurchaseUseCase = get(),
            getAllPurchasesUseCase = get(),
            updatePurchaseUseCase = get(),
            deleteAllRollsByGroupId = get()
        )
    }

    viewModel<RollViewModel>() {
        RollViewModel(
            addRollUseCase = get(),
            deleteRollUseCase = get(),
            getAllRolsIdUseCase = get(),
            updateRollUseCase = get(),
            nameOfToolbarUseCase = get(),
            nameOfPurchaseUseCase = get()
        )
    }
}