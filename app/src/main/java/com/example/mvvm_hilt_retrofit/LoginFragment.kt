package com.example.mvvm_hilt_retrofit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mvvm_hilt_retrofit.databinding.FragmentLoginBinding
import com.example.mvvm_hilt_retrofit.models.UserRequest
import com.example.mvvm_hilt_retrofit.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {

    var _binding: FragmentLoginBinding? = null
    val binding get() = _binding!!

    private val authViewModel by viewModels<AuthViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val validationResult = validateUserInput()
            if(validationResult.first){
            authViewModel.loginUser(getUserRequest())
            }else{
                binding.txtError.text = validationResult.second
            }
        }

        binding.btnSignUp.setOnClickListener {
            findNavController().popBackStack()
        }

        bindObservers()

    }

    private fun getUserRequest(): UserRequest {
        val username = binding.txtUsername.text.toString()
        val password = binding.txtPassword.text.toString()
        return UserRequest(username, password)
    }

    private fun validateUserInput(): Pair<Boolean, String> {
        val userRequest = getUserRequest()
        return authViewModel.validateCredentials(userRequest.username, userRequest.password)
    }

    private fun bindObservers() {
        authViewModel.userResponseLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            when(it) {
                is NetworkResult.Success -> {
                    //token
                    Log.i("token", it.data!!.token)
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                }

                is NetworkResult.Error -> {
                    binding.txtError.text = it.message
                }
                is NetworkResult.Loading -> {
                    //binding.progressBar.visibility = View.VISIBLE
                    binding.progressBar.isVisible = true
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}