package com.ebuozturk.order.dto.address


data class Address @JvmOverloads constructor(
    val id: String,
    val addressName: String,
    val phoneNumber: String,
    val country: String,
    val city: String,
    val firstName:String,
    val lastName:String,
    val fullAddress:String
    ){}