package com.guidoperre.meli.entities.product

import com.google.gson.annotations.SerializedName

data class FilterValue (

	@SerializedName("id")
	val id : String?,

	@SerializedName("name")
	val name : String?,

	@SerializedName("path_from_root")
	val pathsFromRoot : List<PathFromRoot>?

)