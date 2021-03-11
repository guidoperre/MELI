package com.guidoperre.meli.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.guidoperre.meli.entities.search.RecentSearch

@Dao
interface RecentSearchDAO {

    @Query("SELECT * FROM recent_searches LIMIT 20")
    fun getLiveData(): LiveData<List<RecentSearch>>

    @Query("SELECT * FROM recent_searches LIMIT 20")
    fun getAll(): List<RecentSearch>

    @Insert
    fun insert(recentSearch: RecentSearch): Long

}