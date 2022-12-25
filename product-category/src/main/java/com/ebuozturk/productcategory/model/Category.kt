package com.ebuozturk.productcategory.model;

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*;

@Entity
data class Category @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    val id:String? = "",
    val name:String,
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "category")
    val products: Set<Product>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Category

        if (id != null && id != other.id) return false
        if (name != other.name) return false
        if (products != other.products) return false

        return true
    }

}
