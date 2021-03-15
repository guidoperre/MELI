package com.guidoperre.meli.application.modules.entities

import com.guidoperre.meli.repositories.SiteRepository
import com.guidoperre.meli.room.AppDatabase
import com.guidoperre.meli.room.repository.SiteRepositoryImpl
import org.koin.dsl.module

val siteModule = module {

    single<SiteRepository>(override = true){
        SiteRepositoryImpl(
            get(),
            get()
        )
    }

    single { get<AppDatabase>().siteDAO() }

}