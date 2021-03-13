package com.guidoperre.meli.entities.product

import com.google.gson.annotations.SerializedName
import com.guidoperre.meli.entities.product.result.ProductResult

data class ProductSearch (

	@SerializedName("site_id")
	val siteId : String?,

	@SerializedName("query")
	val query : String?,

	@SerializedName("paging")
	val paging : Paging?,

	@SerializedName("results")
	val results : List<ProductResult>?,

)