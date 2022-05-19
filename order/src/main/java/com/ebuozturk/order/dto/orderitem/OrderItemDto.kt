package com.ebuozturk.order.dto.orderitem

import java.time.LocalDateTime

data class OrderItemDto @JvmOverloads constructor (
    val id: String? = "",
    val createdDate:LocalDateTime,
    val quantity:Int,
//    val product:ProductDto
){}
