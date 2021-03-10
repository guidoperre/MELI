package com.guidoperre.meli.network.endpoints

import com.guidoperre.meli.entities.product.ProductSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MercadolibreAPI {

    @GET("/sites/{SITE_ID}/search")
    suspend fun getProducts(
        @Path("SITE_ID") siteId: String,
        @Query("q") query: String,
    ): Response<ProductSearch>

}