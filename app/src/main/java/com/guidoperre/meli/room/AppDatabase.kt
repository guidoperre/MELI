package com.guidoperre.meli.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.guidoperre.meli.entities.search.RecentSearch
import com.guidoperre.meli.room.dao.RecentSearchDAO

@Database(entities = [
        RecentSearch::class
    ],
    version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recentSearchDAO(): RecentSearchDAO

}