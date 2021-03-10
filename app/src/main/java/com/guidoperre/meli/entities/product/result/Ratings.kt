package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName

data class Ratings (

	@SerializedName("negative")
	val negative : Double?,

	@SerializedName("positive")
	val positive : Double?,

	@SerializedName("neutral")
	val neutral : Double?


)