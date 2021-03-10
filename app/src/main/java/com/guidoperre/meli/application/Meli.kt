package com.guidoperre.meli.application

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.guidoperre.meli.application.modules.databaseModule
import com.guidoperre.meli.application.modules.network.apiModule
import com.guidoperre.meli.application.modules.network.apiRepositoryModule
import com.guidoperre.meli.application.modules.network.networkModule
import com.guidoperre.meli.application.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Meli: Application(){

    override fun onCreate() {
        super.onCreate()
        //Inicio koin
        initiateKoin()
        //Activo crashlytics
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
    }

    private fun initiateKoin() {
        //Start Koin!
        startKoin {
            //Declaro el logger en DEBUG
            androidLogger(Level.DEBUG)
            //Declaro el uso de AndroidContext
            androidContext(this@Meli)
            //Declaro los modulos
            modules(
                listOf(
                    //Modulo de los ViewModels
                    viewModelModule,
                    //Modulos network
                    networkModule, apiModule, apiRepositoryModule,
                    //Modulo de la base de datos
                    databaseModule
                )
            )
        }
    }

}