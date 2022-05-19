package com.ebuozturk.order.dto.address


data class Address @JvmOverloads constructor(
    val id:String? = "",
    val addressName:String,
    val phoneNumber:String,
    val firstName:String,
    val lastName:String,
    val country:String,
    val city:String,
    val fullAddress:String
    ){}