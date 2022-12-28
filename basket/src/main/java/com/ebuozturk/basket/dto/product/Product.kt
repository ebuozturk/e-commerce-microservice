package com.ebuozturk.basket.dto.product;

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*;

data class Product @JvmOverloads constructor(
    val id:String,
    val name: String,
    val description: String,
    val unitPrice: Double,
    val quantityPerUnit: Int,
    val unitsInStock: Int,

){

}
