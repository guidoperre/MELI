package com.guidoperre.meli.entities.product

import com.google.gson.annotations.SerializedName

data class AvailableFilter (

	@SerializedName("id")
	val id : String?,

	@SerializedName("name")
	val name : String?,

	@SerializedName("type")
	val type : String?,

	@SerializedName("values")
	val values : List<AvailableFilterValue>?

)