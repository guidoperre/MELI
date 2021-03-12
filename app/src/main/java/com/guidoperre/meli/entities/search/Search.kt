package com.guidoperre.meli.entities.search

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_searches")
data class Search (

    @PrimaryKey(autoGenerate = true)
    var id: Long,

    var history: Boolean,

    var name: String,

)