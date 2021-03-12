package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName

data class PricesInfo (

	@SerializedName("id")
	val id : String?,

	@SerializedName("prices")
	val prices : List<Prices>?,

	@SerializedName("presentation")
	val presentation : Presentation?,

)