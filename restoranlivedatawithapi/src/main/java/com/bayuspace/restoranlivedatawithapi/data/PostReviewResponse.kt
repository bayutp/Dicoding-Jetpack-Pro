package com.bayuspace.restoranlivedatawithapi.data

import com.google.gson.annotations.SerializedName

data class PostReviewResponseResponse(

	@field:SerializedName("customerReviews")
	val customerReviews: List<CustomerReviewsItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)
