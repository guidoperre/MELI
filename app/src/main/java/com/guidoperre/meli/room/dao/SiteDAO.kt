package com.guidoperre.meli.room.dao

import androidx.room.*
import com.guidoperre.meli.entities.sites.Site

@Dao
interface SiteDAO {

    @Query("SELECT * FROM sites LIMIT 1")
    fun get(): Site?

    @Insert
    fun insert(site: Site): Long

    @Query("DELETE FROM sites")
    fun delete()

}