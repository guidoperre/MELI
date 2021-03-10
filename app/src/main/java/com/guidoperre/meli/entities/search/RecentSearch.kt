package com.guidoperre.meli.entities.search

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_searches")
data class RecentSearch (

    @PrimaryKey(autoGenerate = true)
    var id: Long,

    var name: String,

)