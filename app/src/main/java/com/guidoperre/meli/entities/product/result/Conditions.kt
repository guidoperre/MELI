package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName

data class Conditions (

	@SerializedName("eligible")
	val eligible : Boolean?

)