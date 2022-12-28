package com.ebuozturk.user.dto.address;


import kotlin.jvm.JvmOverloads;

data class AddressDto @JvmOverloads constructor(
    val id: String,
    val addressName: String,
    val phoneNumber: String,
    val country: String,
    val city: String,
    val firstName:String,
    val lastName:String,
    val fullAddress:String
) {


}
