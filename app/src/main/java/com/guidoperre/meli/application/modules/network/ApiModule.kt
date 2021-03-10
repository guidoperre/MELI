package com.guidoperre.meli.application.modules.network

import com.guidoperre.meli.network.endpoints.GoogleAPI
import com.guidoperre.meli.network.endpoints.MercadolibreAPI
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideGoogleApi(retrofit: Retrofit): GoogleAPI {
        return retrofit.create(GoogleAPI::class.java)
    }

    fun provideMercadolibreAPI(retrofit: Retrofit): MercadolibreAPI {
        return retrofit.create(MercadolibreAPI::class.java)
    }

    single { provideGoogleApi(get(named("google"))) }
    single { provideMercadolibreAPI(get(named("mercadolibre"))) }

}

