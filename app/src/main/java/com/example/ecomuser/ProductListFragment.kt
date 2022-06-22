package com.example.ecomuser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ecomuser.databinding.FragmentProductListBinding
import com.example.ecomuser.viewmodels.LoginViewModel

class ProductListFragment : Fragment() {
    private val loginViewModel: LoginViewModel by activityViewModels()
    private lateinit var binding: FragmentProductListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        loginViewModel.authLD.observe(viewLifecycleOwner) {
            if (it == LoginViewModel.Authentication.UNAUTHENTICATED) {
                findNavController().navigate(R.id.action_productListFragment_to_loginFragment)
            }
        }
        binding.logoutBtn.setOnClickListener {
            loginViewModel.logout()
        }

        return binding.root
    }

}