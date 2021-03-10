package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName

data class SellerReputation (

	@SerializedName("transactions")
	val transactions : Transactions?,

	@SerializedName("power_seller_status")
	val powerSellerStatus : String?,

	@SerializedName("metrics")
	val metrics : Metrics?,

	@SerializedName("level_id")
	val levelId : String?
)