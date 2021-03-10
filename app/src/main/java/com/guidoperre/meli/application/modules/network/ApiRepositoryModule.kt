package com.guidoperre.meli.application.modules.network

import com.guidoperre.meli.network.endpoints.GoogleAPI
import com.guidoperre.meli.network.endpoints.MercadolibreAPI
import com.guidoperre.meli.network.repositories.GoogleRepositoryImpl
import com.guidoperre.meli.network.repositories.MercadolibreRepositoryImpl
import com.guidoperre.meli.repositories.GoogleRepository
import com.guidoperre.meli.repositories.MercadolibreRepository
import org.koin.dsl.module

val apiRepositoryModule = module {

    fun provideGoogleRepository(api : GoogleAPI): GoogleRepository{
        return GoogleRepositoryImpl(api)
    }

    fun provideMercadolibreRepository(api : MercadolibreAPI): MercadolibreRepository {
        return MercadolibreRepositoryImpl(api)
    }

    single { provideGoogleRepository(get()) }
    single { provideMercadolibreRepository(get()) }

}