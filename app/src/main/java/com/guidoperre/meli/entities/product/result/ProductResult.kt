package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName

data class ProductResult (

	@SerializedName("id")
	val id: String?,

	@SerializedName("site_id")
	val siteId: String?,

	@SerializedName("title")
	val title: String?,

	@SerializedName("seller")
	val seller: Seller?,

	@SerializedName("price")
	val price: Double?,

	@SerializedName("currency_id")
	val currencyId: String?,

	@SerializedName("available_quantity")
	val availableQuantity: Int?,

	@SerializedName("sold_quantity")
	val soldQuantity: Int?,

	@SerializedName("condition")
	val condition: String?,

	@SerializedName("thumbnail")
	val thumbnail: String?,

	@SerializedName("accepts_mercadopago")
	val acceptsMercadopago: Boolean?,

	@SerializedName("address")
	val address: Address?,

	@SerializedName("shipping")
	val shipping: Shipping?,

	@SerializedName("attributes")
	val attributes: List<Attribute>?,

	@SerializedName("category_id")
	val categoryId: String?,

	@SerializedName("domain_id")
	val domainId: String?,

	@SerializedName("catalog_product_id")
	val catalogProductId: String?

)