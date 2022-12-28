package com.ebuozturk.user.dto.address;

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Pattern

data class CreateAddressRequest @JvmOverloads constructor(

    @field:NotEmpty
    val addressName: String,
    @field:Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}\$")
    val phoneNumber: String,
    @field:NotEmpty
    val country: String,
    @field:NotEmpty
    val city: String,
    @field:NotEmpty
    val firstName:String,
    @field:NotEmpty
    val lastName:String,
    @field:NotEmpty
    val fullAddress:String,
    @field:NotEmpty
    val userId: String
){
}
