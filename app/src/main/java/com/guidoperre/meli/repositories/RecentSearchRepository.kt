package com.guidoperre.meli.repositories

import com.guidoperre.meli.entities.search.Search

interface RecentSearchRepository {

    suspend fun getAllRecentSearches():List<Search>

    suspend fun insertSearch(search: Search)

}