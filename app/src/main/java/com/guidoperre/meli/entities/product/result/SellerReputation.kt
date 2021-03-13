package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName

data class SellerReputation (

        @SerializedName("power_seller_status")
	val powerSellerStatus : String?,

        @SerializedName("metrics")
	val metric : Metric?,

        @SerializedName("level_id")
	val levelId : String?
)