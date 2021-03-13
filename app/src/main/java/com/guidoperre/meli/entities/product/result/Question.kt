package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName

data class Question (

    @SerializedName("status")
    val status: String?,

    @SerializedName("text")
    val text: String?,

    @SerializedName("answer")
    val answer: Answer?

)