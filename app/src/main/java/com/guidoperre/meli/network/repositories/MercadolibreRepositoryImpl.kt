package com.guidoperre.meli.network.repositories

import android.util.Log
import com.guidoperre.meli.entities.product.ProductSearch
import com.guidoperre.meli.entities.product.result.Description
import com.guidoperre.meli.entities.product.result.ProductPicture
import com.guidoperre.meli.entities.product.result.ProductQuestion
import com.guidoperre.meli.entities.product.result.Review
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

    override suspend fun getPictures(productId: String): ProductPicture? {
        return try {
            val response = api.getPictures(productId)
            if (response.isSuccessful) {
                response.body()
            } else
                null
        } catch (e: Exception) {
            Log.i("getPictures", "Error" + e.message)
            null
        }
    }

    override suspend fun getDescription(productId: String): Description? {
        return try {
            val response = api.getDescription(productId)
            if (response.isSuccessful) {
                response.body()
            } else
                null
        } catch (e: Exception) {
            Log.i("getDescription", "Error" + e.message)
            null
        }
    }

    override suspend fun getReviews(productId: String): Review? {
        return try {
            val response = api.getReviews(productId)
            if (response.isSuccessful) {
                response.body()
            } else
                null
        } catch (e: Exception) {
            Log.i("getReviews", "Error" + e.message)
            null
        }
    }

    override suspend fun getQuestions(item: String, apiVersion: Int): ProductQuestion? {
        return try {
            val response = api.getQuestions(item, apiVersion)
            if (response.isSuccessful) {
                response.body()
            } else
                null
        } catch (e: Exception) {
            Log.i("getQuestions", "Error" + e.message)
            null
        }
    }


}