package com.ebuozturk.basket.dto.basketproduct;

import com.ebuozturk.basket.dto.product.ProductDto


data class BasketProductDto @JvmOverloads constructor(
    val id:String,
    val quantity:Int,
    val basketId:String,
    val productId: String
){

}