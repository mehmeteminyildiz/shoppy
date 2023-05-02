package com.mhmtyldz.shoppy.model.cart

import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("id"              ) var id              : Int?                = null,
    @SerializedName("products"        ) var products        : ArrayList<Products> = arrayListOf(),
    @SerializedName("total"           ) var total           : Int?                = null,
    @SerializedName("discountedTotal" ) var discountedTotal : Int?                = null,
    @SerializedName("userId"          ) var userId          : Int?                = null,
    @SerializedName("totalProducts"   ) var totalProducts   : Int?                = null,
    @SerializedName("totalQuantity"   ) var totalQuantity   : Int?                = null
)
