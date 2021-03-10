package com.guidoperre.meli.application.modules.network

import com.guidoperre.meli.network.endpoints.GoogleAPI
import com.guidoperre.meli.network.endpoints.MercadolibreAPI
import org.koin.dsl.module

val apiRepositoryModule = module {

    fun provideGoogleRepository(api : GoogleAPI): AutoCompleteRepository{
        return AutoCompleteRepositoryImpl(api)
    }

    fun provideMercadolibreRepository(api : MercadolibreAPI): GeocoderRepository {
        return GeocoderRepositoryImpl(api)
    }

    single { provideGoogleRepository(get()) }
    single { provideMercadolibreRepository(get()) }

}