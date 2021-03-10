package com.guidoperre.meli.entities.product

import com.google.gson.annotations.SerializedName

data class PathFromRoot (

	@SerializedName("id")
	val id : String?,

	@SerializedName("name")
	val name : String?

)