package com.guidoperre.meli.application.modules.network

import com.guidoperre.meli.network.interceptor.TimeOutInterceptor
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.interceptors().add(TimeOutInterceptor())
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    /*
    Establezco el path segun el nombramiento
    */
    single { provideHttpClient() }
    single (named("google")) {
        provideRetrofit(
            get(),
            "http://suggestqueries.google.com/"
        )
    }
    single (named("mercadolibre")) {
        provideRetrofit(
                get(),
                "https://api.mercadolibre.com/"
        )
    }

}
