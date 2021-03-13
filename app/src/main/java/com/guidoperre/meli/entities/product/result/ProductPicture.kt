package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName

data class ProductPicture (

    @SerializedName("pictures")
    val pictures: List<Question>?

)