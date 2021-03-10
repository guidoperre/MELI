package com.guidoperre.meli.repositories

import com.guidoperre.meli.entities.product.ProductSearch

interface MercadolibreRepository {

    suspend fun getProducts(siteId: String, q: String): ProductSearch?

}