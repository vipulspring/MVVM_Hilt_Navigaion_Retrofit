package com.example.mvvm_hilt_retrofit.repository

import android.util.Log
import com.example.mvvm_hilt_retrofit.api.UserAPI
import com.example.mvvm_hilt_retrofit.models.UserRequest
import javax.inject.Inject

class UserRepository @Inject constructor(private val userAPI: UserAPI) {

    suspend fun registerUser(userRequest: UserRequest) {
        val response = userAPI.signup(userRequest)
        Log.d("UserRepository", response.body().toString())
    }

    suspend fun loginUser(userRequest: UserRequest) {
        val response = userAPI.signin(userRequest)
        Log.d("UserRepository", response.body().toString())
    }

}