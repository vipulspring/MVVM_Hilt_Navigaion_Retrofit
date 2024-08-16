package com.example.mvvm_hilt_retrofit.api

import com.example.mvvm_hilt_retrofit.models.UserRequest
import com.example.mvvm_hilt_retrofit.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {

    //'https://dummyjson.com/auth/me'
    @POST("auth/me")
    suspend fun signup(@Body userRequest: UserRequest): Response<UserResponse>

    @POST("auth/login")
    suspend fun signin(@Body userRequest: UserRequest): Response<UserResponse>
}
