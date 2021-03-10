package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName

data class Presentation (

	@SerializedName("display_currency")
	val displayCurrency : String?

)