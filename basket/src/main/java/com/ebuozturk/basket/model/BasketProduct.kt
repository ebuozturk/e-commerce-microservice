package com.ebuozturk.basket.model;

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*;

@Entity
data class BasketProduct @JvmOverloads constructor(

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    val id:String? = "",
    val productId:String,
    @ManyToOne
    @JoinColumn(name = "basket_id")
    val basket: Basket,
    var quantity:Int

) {

    fun increaseQuantity(amount:Int){
        this.quantity += amount;
    }
    fun decreaseQuantity(amount: Int){
        if(this.quantity>1){
            if(this.quantity > amount)
              this.quantity -= amount;
            else
                this.quantity = 1;

        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BasketProduct

        if (id != null && id != other.id) return false
        if (productId != other.productId) return false
        if (basket != other.basket) return false
        if (quantity != other.quantity) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + productId.hashCode()
        result = 31 * result + basket.id.hashCode()
        result = 31 * result + quantity
        return result
    }


}
