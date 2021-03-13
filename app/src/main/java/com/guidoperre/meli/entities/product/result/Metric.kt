package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName

data class Metric (

	@SerializedName("sales")
	val sales : Sales?,

)