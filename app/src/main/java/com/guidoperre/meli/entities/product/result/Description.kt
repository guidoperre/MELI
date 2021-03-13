package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName

data class Description (

    @SerializedName("plain_text")
    val text: String?,

)