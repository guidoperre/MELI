package com.guidoperre.meli.entities.product

import com.google.gson.annotations.SerializedName

data class Paging (

	@SerializedName("total")
	val total : Int?,

	@SerializedName("offset")
	val offset : Int?,

)