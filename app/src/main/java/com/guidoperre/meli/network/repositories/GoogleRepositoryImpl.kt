package com.guidoperre.meli.network.repositories

import android.util.Log
import com.guidoperre.meli.entities.search.Search
import com.guidoperre.meli.network.endpoints.GoogleAPI
import com.guidoperre.meli.repositories.GoogleRepository
import com.guidoperre.meli.utils.Tools

class GoogleRepositoryImpl(
    private val api: GoogleAPI
): GoogleRepository {

    override suspend fun getSuggestions(q: String): List<Search>? {
        return try {
            val response = api.getSuggests("chrome", q)
            if (response.isSuccessful) {
                Tools.parseGoogleResponse(response.body())
            } else
                null
        } catch (e: Exception) {
            Log.i("getSuggestions", "Error" + e.message)
            null
        }
    }

}