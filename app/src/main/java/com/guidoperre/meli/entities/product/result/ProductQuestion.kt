package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName

data class ProductQuestion (

    @SerializedName("questions")
    val questions: List<Question>?,

)