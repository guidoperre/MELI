package com.guidoperre.meli.application.modules

import androidx.room.Room
import com.guidoperre.meli.room.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/*
   Creo la base de datos
*/
val databaseModule = module {

    single {
        Room.databaseBuilder(
                androidApplication(),
                AppDatabase::class.java,
                "meli-db"
            )
            .fallbackToDestructiveMigration()
            .build()
    }
}