package com.guidoperre.meli.entities.product.result

import com.google.gson.annotations.SerializedName

data class Metrics (

	@SerializedName("claims")
	val claims : Claims?,

	@SerializedName("delayed_handling_time")
	val delayedHandlingTime : DelayedHandlingTime?,

	@SerializedName("sales")
	val sales : Sales?,

	@SerializedName("cancellations")
	val cancellations : Cancellations?

)