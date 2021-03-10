package com.guidoperre.meli.entities.product

import com.google.gson.annotations.SerializedName

data class AvailableFilterValue (

	@SerializedName("id")
	val id : String?,

	@SerializedName("name")
	val name : String?,

	@SerializedName("results")
	val results : Int?

)