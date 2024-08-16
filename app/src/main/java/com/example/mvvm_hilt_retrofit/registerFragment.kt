package com.example.mvvm_hilt_retrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mvvm_hilt_retrofit.databinding.FragmentRegisterBinding
import com.example.mvvm_hilt_retrofit.models.UserRequest
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class registerFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val authViewModel by viewModels<AuthViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)


        binding.btnSignUp.setOnClickListener {
           // findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
            authViewModel.registerUser(UserRequest("Vipul", "123456"))
        }


        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}