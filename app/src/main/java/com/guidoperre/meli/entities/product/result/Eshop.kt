package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName

data class Eshop (

	@SerializedName("nick_name")
	val nickName : String?,

	@SerializedName("eshop_id")
	val eshopId : Int?,

	@SerializedName("eshop_locations")
	val eshopLocations : List<String>?,

	@SerializedName("site_id")
	val siteId : String?,

	@SerializedName("eshop_logo_url")
	val eshopLogoUrl : String?,

	@SerializedName("eshop_status_id")
	val eshopStatusId : Int?,

	@SerializedName("seller")
	val seller : Int?,

	@SerializedName("eshop_experience")
	val eshopExperience : Int?

)