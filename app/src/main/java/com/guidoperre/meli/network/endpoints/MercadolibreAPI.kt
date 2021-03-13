package com.guidoperre.meli.network.endpoints

import com.guidoperre.meli.entities.product.ProductSearch
import com.guidoperre.meli.entities.product.result.*
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
        @Query("offset") offset: Int
    ): Response<ProductSearch>

    @GET("/items/{PRODUCT_ID}")
    suspend fun getPictures(
            @Path("PRODUCT_ID") productId: String,
    ): Response<ProductPicture>

    @GET("/items/{PRODUCT_ID}/description")
    suspend fun getDescription(
            @Path("PRODUCT_ID") productId: String,
    ): Response<Description>

    @GET("/reviews/item/{PRODUCT_ID}")
    suspend fun getReviews(
            @Path("PRODUCT_ID") productId: String,
    ): Response<Review>

    @GET("/questions/search")
    suspend fun getQuestions(
            @Query("item") item: String,
            @Query("api_version") apiVersion: Int
    ): Response<ProductQuestion>

}