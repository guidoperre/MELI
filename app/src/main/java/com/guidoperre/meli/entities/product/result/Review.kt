package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName
import com.guidoperre.meli.entities.product.Paging

data class Review (

    @SerializedName("paging")
    val paging: Paging?,

    @SerializedName("rating_average")
    val ratingAverage: Double?,

)