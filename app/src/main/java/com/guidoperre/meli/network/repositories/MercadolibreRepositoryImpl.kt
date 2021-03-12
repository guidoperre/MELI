package com.guidoperre.meli.network.repositories

import android.util.Log
import com.guidoperre.meli.entities.product.ProductSearch
import com.guidoperre.meli.network.endpoints.MercadolibreAPI
import com.guidoperre.meli.repositories.MercadolibreRepository

class MercadolibreRepositoryImpl(
    private val api: MercadolibreAPI
): MercadolibreRepository {

    override suspend fun getProducts(siteId: String, q: String, offset: Int): ProductSearch? {
        return try {
            val response = api.getProducts(siteId, q, offset)
            if (response.isSuccessful) {
                response.body()
            } else
                null
        } catch (e: Exception) {
            Log.i("getProducts", "Error" + e.message)
            null
        }
    }

}