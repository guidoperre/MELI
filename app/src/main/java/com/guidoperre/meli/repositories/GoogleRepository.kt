package com.guidoperre.meli.repositories

import com.guidoperre.meli.entities.search.Search

interface GoogleRepository {

    suspend fun getSuggestions(q: String): List<Search>?

}