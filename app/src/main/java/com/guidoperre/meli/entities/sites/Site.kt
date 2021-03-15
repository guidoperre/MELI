package com.guidoperre.meli.entities.sites

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "sites")
data class Site (

    @PrimaryKey
    var id: String,

    @Ignore
    var name: String?

){
    constructor():this("MLA","")
}