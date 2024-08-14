package com.example.mvvm_hilt_retrofit.models

data class UserResponse(
    val email: String,
    val firstName: String,
    val gender: String,
    val id: Int,
    val image: String,
    val lastName: String,
    val refreshToken: String,
    val token: String,
    val username: String
)