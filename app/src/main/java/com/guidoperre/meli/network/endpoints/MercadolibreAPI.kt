package com.guidoperre.meli.network.endpoints

import com.guidoperre.meli.entities.product.ProductSearch
import com.guidoperre.meli.entities.product.result.*
import com.guidoperre.meli.entities.sites.Site
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MercadolibreAPI {

    /*
    API de busqueda de articulos
    */
    @GET("/sites/{SITE_ID}/search")
    suspend fun getProducts(
        @Path("SITE_ID") siteId: String,
        @Query("q") query: String,
        @Query("offset") offset: Int
    ): Response<ProductSearch>

    /*
    API de detalle del item
    */
    @GET("/items/{PRODUCT_ID}")
    suspend fun getPictures(
            @Path("PRODUCT_ID") productId: String,
    ): Response<ProductPicture>


    /*
    API de descripcion del item
    */
    @GET("/items/{PRODUCT_ID}/description")
    suspend fun getDescription(
            @Path("PRODUCT_ID") productId: String,
    ): Response<Description>


    /*
    API de opiniones del item
    */
    @GET("/reviews/item/{PRODUCT_ID}")
    suspend fun getReviews(
            @Path("PRODUCT_ID") productId: String,
    ): Response<Review>


    /*
    API de preguntas sobre item
    */
    @GET("/questions/search")
    suspend fun getQuestions(
            @Query("item") item: String,
            @Query("api_version") apiVersion: Int
    ): Response<ProductQuestion>


    /*
    API de sitios disponibles
    */
    @GET("/sites")
    suspend fun getSites(
    ): Response<List<Site>>

}