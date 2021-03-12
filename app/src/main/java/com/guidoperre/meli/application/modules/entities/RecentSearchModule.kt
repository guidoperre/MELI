package com.guidoperre.meli.application.modules.entities

import com.guidoperre.meli.repositories.RecentSearchRepository
import com.guidoperre.meli.room.AppDatabase
import com.guidoperre.meli.room.repository.RecentSearchRepositoryImpl
import org.koin.dsl.module

val recentSearchModule = module {

    single<RecentSearchRepository>(override = true){
        RecentSearchRepositoryImpl(
            get(),
            get()
        )
    }

    single { get<AppDatabase>().recentSearchDAO() }

}