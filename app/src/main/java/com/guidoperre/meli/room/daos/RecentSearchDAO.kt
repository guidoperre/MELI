package com.guidoperre.meli.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import com.guidoperre.meli.entities.search.RecentSearch

interface RecentSearchDAO {

    @Query("SELECT * FROM recent_searches LIMIT 20")
    fun getLiveData(): LiveData<List<RecentSearch>>

    @Query("SELECT * FROM recent_searches LIMIT 20")
    fun getAll(): List<RecentSearch>

    @Insert
    fun insert(recentSearch: RecentSearch): Long

}