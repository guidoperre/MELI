package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName

data class Attributes (

	@SerializedName("values")
	val values : List<Values>?,

	@SerializedName("attribute_group_name")
	val attributeGroupName : String?,

	@SerializedName("id")
	val id : String?,

	@SerializedName("name")
	val name : String?,

	@SerializedName("value_id")
	val valueId : Int?,

	@SerializedName("value_name")
	val valueName : String?,

	@SerializedName("attribute_group_id")
	val attributeGroupId : String?,

	@SerializedName("source")
	val source : Double?

)