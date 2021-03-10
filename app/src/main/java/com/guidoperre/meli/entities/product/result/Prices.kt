package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName

data class Prices (

    @SerializedName("id")
	val id : String?,

    @SerializedName("type")
	val type : String?,

    @SerializedName("conditions")
	val conditions : Conditions?,

    @SerializedName("amount")
    val amount : Double?,

    @SerializedName("currency_id")
    val currencyId : String?,

    @SerializedName("exchange_rate_context")
    val exchangeRateContext : String?,

    @SerializedName("last_updated")
    val lastUpdated : String?,

)