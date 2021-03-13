package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName

data class Shipping (

	@SerializedName("free_shipping")
	val freeShipping : Boolean?

)