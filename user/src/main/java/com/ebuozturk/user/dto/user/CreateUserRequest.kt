package com.ebuozturk.user.dto.user;


data class CreateUserRequest @JvmOverloads constructor(
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val email: String,
    val password: String
) {

}
