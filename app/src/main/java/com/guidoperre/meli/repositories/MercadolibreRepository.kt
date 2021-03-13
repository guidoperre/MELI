package com.guidoperre.meli.repositories

import com.guidoperre.meli.entities.product.ProductSearch
import com.guidoperre.meli.entities.product.result.Description
import com.guidoperre.meli.entities.product.result.ProductPicture
import com.guidoperre.meli.entities.product.result.ProductQuestion
import com.guidoperre.meli.entities.product.result.Review

interface MercadolibreRepository {

    suspend fun getProducts(siteId: String, q: String, offset: Int): ProductSearch?

    suspend fun getPictures(productId: String): ProductPicture?

    suspend fun getDescription(productId: String): Description?

    suspend fun getReviews(productId: String): Review?

    suspend fun getQuestions(item: String, apiVersion: Int): ProductQuestion?

}