package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName

data class SellerAddress (

	@SerializedName("id")
	val id : String?,

	@SerializedName("comment")
	val comment : String?,

	@SerializedName("address_line")
	val addressLine : String?,

	@SerializedName("zip_code")
	val zipCode : String?,

	@SerializedName("country")
	val country : Country?,

	@SerializedName("state")
	val state : State?,

	@SerializedName("city")
	val city : City?

)