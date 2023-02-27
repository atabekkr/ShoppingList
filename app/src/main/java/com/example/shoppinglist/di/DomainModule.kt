package com.example.shoppinglist.di

import com.example.shoppinglist.domain.usecases.allitemusecase.GetAllRollsUseCaseImpl
import com.example.shoppinglist.domain.usecases.purchaseusecase.impl.*
import com.example.shoppinglist.domain.usecases.rollusecase.impl.*
import org.koin.dsl.module

val domainModule = module {

    factory {
        AddPurchaseUseCaseImpl(purchaseRepository = get())
    }

    factory {
        DeletePurchaseUseCaseImpl(purchaseRepository = get())
    }

    factory {
        GetAllPurchasesUseCaseImpl(purchaseRepository = get())
    }

    factory {
        NameOfToolbarUseCaseImpl(purchaseRepository = get())
    }

    factory {
        UpdatePurchaseUseCaseImpl(purchaseRepository = get())
    }

    factory {
        DeleteAllRollsByGroupIdImpl(rollRepository = get())
    }

    /*
     *Roll Di ->
     */

    factory {
        NameOfPurchaseUseCaseImpl(rollRepository = get())
    }

    factory {
        AddRollUseCaseImpl(rollRepository = get())
    }

    factory {
        DeleteRollUseCaseImpl(rollRepository = get())
    }

    factory {
        GetAllRolsIdUseCaseImpl(rollRepository = get())
    }

    factory {
        UpdateRollUseCaseImpl(rollRepository = get())
    }

    /*
     *AllItem
     */
    factory {
        GetAllRollsUseCaseImpl(allItemRepository = get())
    }

}