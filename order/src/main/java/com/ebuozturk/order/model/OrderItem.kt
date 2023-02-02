package com.ebuozturk.order.model;

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class OrderItem @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    val id:String? = "",
    val quantity:Int,
    val price:Double,
    val createdDate: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id",referencedColumnName = "id")
    val order: Order,

    val productId:String,

    @Enumerated(EnumType.ORDINAL)
    val status: Status

){

    constructor(quantity: Int,price: Double,createdDate: LocalDateTime,order: Order,productId: String): this("",quantity,price,createdDate,order,productId,Status.PLANNING)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OrderItem

        if (id != null && id != other.id) return false
        if (quantity != other.quantity) return false
        if (price != other.price) return false
        if (createdDate != other.createdDate) return false
        if (order != other.order) return false
        if (productId != other.productId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + quantity
        result = 31 * result + price.hashCode()
        result = 31 * result + createdDate.hashCode()
        result = 31 * result + order.id.hashCode()
        result = 31 * result + productId.hashCode()
        return result
    }

}

enum class Status(){
    PLANNING, SHIPPING, COMPLETE, CANCELLED,UNFULFILLABLE

}