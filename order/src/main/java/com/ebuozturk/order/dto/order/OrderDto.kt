package com.ebuozturk.order.dto.order

import com.ebuozturk.order.dto.orderaddress.OrderAddressDto
import com.ebuozturk.order.dto.orderitem.OrderItemDto
import java.time.LocalDateTime

data class OrderDto @JvmOverloads constructor(
    val id: String? = "",
    val createdDate: LocalDateTime,
    val totalPrice: Double,
    val items: List<OrderItemDto>,
    val orderAddress: OrderAddressDto,
    val billAddress: OrderAddressDto,
    val status:String
){}
