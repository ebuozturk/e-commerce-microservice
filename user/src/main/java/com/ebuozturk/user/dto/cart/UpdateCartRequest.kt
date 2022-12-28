package com.ebuozturk.user.dto.cart;

import java.util.*

data class UpdateCartRequest @JvmOverloads constructor(
    val name: String,
    val no: String,
    val expiryDate: Date,
    val cvc: String,
    val userId: String
) {
}