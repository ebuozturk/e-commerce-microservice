package com.ebuozturk.order.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
data class OrderAddress @JvmOverloads constructor(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
        val id:String? = "",
        val addressName:String,
        val phoneNumber:String,
        val firstName:String,
        val lastName:String,
        val country:String,
        val city:String,
        val fullAddress:String,


) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OrderAddress

        if (id != other.id) return false
        if (addressName != other.addressName) return false
        if (phoneNumber != other.phoneNumber) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (country != other.country) return false
        if (city != other.city) return false
        if (fullAddress != other.fullAddress) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + addressName.hashCode()
        result = 31 * result + phoneNumber.hashCode()
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + country.hashCode()
        result = 31 * result + city.hashCode()
        result = 31 * result + fullAddress.hashCode()
        return result
    }
}