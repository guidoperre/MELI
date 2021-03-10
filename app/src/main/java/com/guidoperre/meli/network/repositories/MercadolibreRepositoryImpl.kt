package com.guidoperre.meli.network.repositories

import com.guidoperre.meli.network.endpoints.MercadolibreAPI
import com.guidoperre.meli.repositories.MercadolibreRepository

class MercadolibreRepositoryImpl(
    private val api: MercadolibreAPI
): MercadolibreRepository {

    override fun getProducts(q: String) {
        TODO("Not yet implemented")
    }

}