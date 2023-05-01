package com.mhmtyldz.shoppy.model.products

import com.google.gson.annotations.SerializedName

data class ProductsOfCategoryResponse(
    @SerializedName("products" ) var products : ArrayList<Product> = arrayListOf(),
    @SerializedName("total"    ) var total    : Int?                = null,
    @SerializedName("skip"     ) var skip     : Int?                = null,
    @SerializedName("limit"    ) var limit    : Int?                = null,
)