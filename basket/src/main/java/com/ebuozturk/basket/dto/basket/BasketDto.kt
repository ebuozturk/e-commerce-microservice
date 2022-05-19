package com.ebuozturk.basket.dto.basket;

import com.ebuozturk.basket.dto.basketproduct.BasketProductDto

data class BasketDto @JvmOverloads constructor(
    val id:String,
    val userId:String,
    val products: List<BasketProductDto>,
    val totalPrice:Double

){
}

