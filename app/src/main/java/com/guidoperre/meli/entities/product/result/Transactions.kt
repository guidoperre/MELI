package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName

data class Transactions (

	@SerializedName("total")
	val total : Int?,

	@SerializedName("canceled")
	val canceled : Int?,

	@SerializedName("period")
	val period : String?,

	@SerializedName("ratings")
	val ratings : Ratings?,

	@SerializedName("completed")
	val completed : Int?

)