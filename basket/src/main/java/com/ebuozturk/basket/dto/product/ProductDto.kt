package com.ebuozturk.basket.dto.product;

data class ProductDto @JvmOverloads constructor(
    val id: String? = "",
    val name: String,
    val unitPrice: Double,
){

}

