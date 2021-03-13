package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName

data class Seller (

	@SerializedName("seller_reputation")
	val sellerReputation : SellerReputation?

)