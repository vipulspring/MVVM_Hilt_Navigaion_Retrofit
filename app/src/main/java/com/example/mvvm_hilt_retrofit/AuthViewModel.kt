package com.example.mvvm_hilt_retrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_hilt_retrofit.models.UserRequest
import com.example.mvvm_hilt_retrofit.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@HiltViewModel
class AuthViewModel(private val userRepo: UserRepository) : ViewModel() {


    fun registerUser(userRequest: UserRequest){
        viewModelScope.launch {
            //userRepo.registerUser(userRequest)
            delay(2000)
        }
    }

    fun loginUser(userRequest: UserRequest){
        viewModelScope.launch {
            userRepo.loginUser(userRequest)
        }
    }

}