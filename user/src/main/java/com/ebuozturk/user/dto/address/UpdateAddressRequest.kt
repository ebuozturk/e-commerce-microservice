package com.ebuozturk.user.dto.address;

data class UpdateAddressRequest @JvmOverloads constructor(
    val addressName: String,
    val phoneNumber: String,
    val country: String,
    val city: String,
    val firstName:String,
    val lastName:String,
    val fullAddress:String,
    val userId: String
){

}
