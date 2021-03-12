package com.guidoperre.meli.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.guidoperre.meli.entities.search.Search
import com.guidoperre.meli.room.dao.RecentSearchDAO

@Database(entities = [
        Search::class
    ],
    version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recentSearchDAO(): RecentSearchDAO

}