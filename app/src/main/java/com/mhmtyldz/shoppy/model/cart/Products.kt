package com.mhmtyldz.shoppy.model.cart

import com.google.gson.annotations.SerializedName

data class Products(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("price") var price: Int? = null,
    @SerializedName("quantity") var quantity: Int? = null,
    @SerializedName("total") var total: Int? = null,
    @SerializedName("discountPercentage") var discountPercentage: Double? = null,
    @SerializedName("discountedPrice") var discountedPrice: Int? = null

)
