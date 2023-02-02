package com.ebuozturk.order.model

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*
import kotlin.collections.HashSet

@Entity
@Table(name = "orders")
data class Order @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    val id:String? = "",
    val createdDate: LocalDateTime,
    val totalPrice:Double,
    val userId:String,

    @ManyToOne(fetch = FetchType.LAZY,cascade = [CascadeType.ALL])
    @JoinColumn(name = "order_address_id",referencedColumnName = "id")
    val orderAddress: OrderAddress,

    @ManyToOne(fetch = FetchType.LAZY,cascade =[CascadeType.ALL])
    @JoinColumn(name = "bill_address_id",referencedColumnName = "id")
    val billAddress: OrderAddress,

    @OneToMany(mappedBy = "order" ,cascade = [CascadeType.ALL])
    val orderItems: Set<OrderItem>,


){
    constructor(createdDate: LocalDateTime, totalPrice: Double, orderAddress: OrderAddress, billAddress: OrderAddress, userId: String): this("",createdDate,totalPrice,userId,orderAddress,billAddress,HashSet())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Order

        if (id != null && id != other.id) return false
        if (createdDate != other.createdDate) return false
        if (totalPrice != other.totalPrice) return false
        if (orderItems != other.orderItems) return false
        if (orderAddress != other.orderAddress) return false
        if (billAddress != other.billAddress) return false
        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + createdDate.hashCode()
        result = 31 * result + totalPrice.hashCode()
        result = 31 * result + orderItems.hashCode()
        result = 31 * result + orderAddress.id.hashCode()
        result = 31 * result + billAddress.id.hashCode()
        result = 31 * result + userId.hashCode()
        return result
    }

}

