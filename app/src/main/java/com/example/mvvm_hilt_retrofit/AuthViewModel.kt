package com.example.mvvm_hilt_retrofit

import android.text.TextUtils
import android.util.Patterns
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

    fun validateCredentials(
        username: String,
        emailAddress: String = "vipulspring@gmail.com",
        password: String
    ): Pair<Boolean, String> {
        var result = Pair(true, "")
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(emailAddress) || TextUtils.isEmpty(
                password
            )
        ) {
            result = Pair(false, "Please provide the credentials")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            result = Pair(false, "Please provide valid email")
        } else if (password.length <= 5) {
            result = Pair(false, "Password length should be greater than 5")
        }
        return result
    }

}