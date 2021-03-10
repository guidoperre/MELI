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

	@SerializedName("secondary_results")
	val secondaryResults : List<ProductResult>?,

	@SerializedName("related_results")
	val relatedResults : List<ProductResult>?,

	@SerializedName("sort")
	val sort : Sort?,

	@SerializedName("available_sorts")
	val availableSorts : List<AvailableSort>?,

	@SerializedName("filters")
	val filters : List<Filter>?,

	@SerializedName("available_filters")
	val availableFilters : List<AvailableFilter>?

)