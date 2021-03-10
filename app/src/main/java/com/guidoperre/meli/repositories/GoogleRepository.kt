package com.guidoperre.meli.repositories

interface GoogleRepository {

    suspend fun getSuggestions(q: String): List<String>?

}