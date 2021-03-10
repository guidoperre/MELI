package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName

data class Cancellations (

	@SerializedName("rate")
	val rate : Double?,

	@SerializedName("value")
	val value : Int?,

	@SerializedName("period")
	val period : String?

)