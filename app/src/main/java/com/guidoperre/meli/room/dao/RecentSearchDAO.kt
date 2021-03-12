package com.guidoperre.meli.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.guidoperre.meli.entities.search.Search

@Dao
interface RecentSearchDAO {

    @Query("SELECT * FROM recent_searches ORDER BY id DESC LIMIT 20")
    fun getLiveData(): LiveData<List<Search>>

    @Query("SELECT * FROM recent_searches ORDER BY id DESC LIMIT 20")
    fun getAll(): List<Search>

    @Insert
    fun insert(search: Search): Long

}