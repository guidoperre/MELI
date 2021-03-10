package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName

data class Values (

	@SerializedName("id")
	val id : Int?,

	@SerializedName("name")
	val name : String?,

	@SerializedName("source")
	val source : Double?

)