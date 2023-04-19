package com.mhmtyldz.shoppy.model.products

data class ProductsOfCategoryResponse(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)