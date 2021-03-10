package com.guidoperre.meli.network.endpoints

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleAPI {

    @GET("/complete/search")
    suspend fun getAutoSuggest(
            @Query("client") client: String,
            @Query("q") query: String,
    ): Response<String>

}