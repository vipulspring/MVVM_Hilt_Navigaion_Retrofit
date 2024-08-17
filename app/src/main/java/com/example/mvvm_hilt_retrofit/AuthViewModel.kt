package com.example.mvvm_hilt_retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_hilt_retrofit.models.UserRequest
import com.example.mvvm_hilt_retrofit.models.UserResponse
import com.example.mvvm_hilt_retrofit.repository.UserRepository
import com.example.mvvm_hilt_retrofit.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepo: UserRepository) : ViewModel() {

    val userResponseLiveData: LiveData<NetworkResult<UserResponse>>
        get() = userRepo.userResponseLiveData

    fun registerUser(userRequest: UserRequest) {
        viewModelScope.launch {
            userRepo.registerUser(userRequest)
            //delay(2000)
        }
    }

    fun loginUser(userRequest: UserRequest) {
        viewModelScope.launch {
            userRepo.loginUser(userRequest)
        }
    }

}