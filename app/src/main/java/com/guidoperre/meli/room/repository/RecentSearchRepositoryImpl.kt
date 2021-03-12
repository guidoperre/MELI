package com.guidoperre.meli.room.repository

import com.guidoperre.meli.entities.search.Search
import com.guidoperre.meli.repositories.RecentSearchRepository
import com.guidoperre.meli.room.AppDatabase
import com.guidoperre.meli.room.dao.RecentSearchDAO

class RecentSearchRepositoryImpl(
    private val roomDatabase: AppDatabase,
    private val recentSearchDAO: RecentSearchDAO
): RecentSearchRepository {

    override suspend fun getAllRecentSearches(): List<Search> {
        return recentSearchDAO.getAll()
    }

    override suspend fun insertSearch(search: Search) {
        roomDatabase.runInTransaction {
            val history = recentSearchDAO.getAll()
            var exist = false
            for (recentSearch in history)
                if (recentSearch.name == search.name)
                    exist = true
            if (!exist){
                search.history = true
                recentSearchDAO.insert(search)
            }
        }
    }

}