package com.ebuozturk.basket.model;

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*;

@Entity
data class Basket @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    val id:String? = "",
    @Column(unique = true)
    val userId:String,
    @OneToMany(mappedBy = "basket")
    val products:Set<BasketProduct>
) {

    fun isBasketProductExist(product: BasketProduct) : Boolean{
        return products.contains(product);
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Basket

        if (id != null && id != other.id) return false
        if (userId != other.userId) return false
        if (products != other.products) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + userId.hashCode()
        result = 31 * result + products.hashCode()
        return result
    }

    override fun toString(): String {
        return "Basket(id=$id, userId=${userId}, products=${products.hashCode()})"
    }


}
