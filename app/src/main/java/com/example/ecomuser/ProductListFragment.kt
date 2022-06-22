package com.example.ecomuser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecomuser.databinding.FragmentProductListBinding
import com.example.ecomuser.models.Product
import com.example.ecomuser.utils.CartAction
import com.example.ecomuser.viewmodels.LoginViewModel
import com.example.ecomuser.viewmodels.ProductViewModel
import com.example.ecomuserbatch4.adapters.ProductAdapter
import com.example.ecomuserbatch4.models.CartItem
import com.example.ecomuserbatch4.viewmodels.UserViewModel

class ProductListFragment : Fragment() {
    private val loginViewModel: LoginViewModel by activityViewModels()
    private val productViewModel: ProductViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()


    private lateinit var binding: FragmentProductListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(inflater, container, false)

        val adapter = ProductAdapter { action, cartItem ->
            performCartAction(action, cartItem)
        }

        val glm = GridLayoutManager(requireActivity(), 2)
        binding.productRV.layoutManager = glm
        binding.productRV.adapter = adapter
        userViewModel.getAllCartItems(loginViewModel.auth.currentUser?.uid!!)
            .observe(viewLifecycleOwner) {cartList ->
                productViewModel.getProducts().observe(viewLifecycleOwner) {productList ->
                    val tempProductList = mutableListOf<Product>()
                    for (product in productList) {
                        for (item in cartList) {
                            if (product.id == item.productId) {
                                product.inCart = true
                            }
                        }
                        tempProductList.add(product)
                    }
                    adapter.submitList(tempProductList)
                }
            }

        loginViewModel.authLD.observe(viewLifecycleOwner) {
            if (it == LoginViewModel.Authentication.UNAUTHENTICATED) {
                findNavController().navigate(R.id.action_productListFragment_to_loginFragment)
            }
        }

        return binding.root
    }

    fun performCartAction(action: CartAction, cartItem: CartItem) {
        when (action) {
            CartAction.ADD -> {
                userViewModel.addToCart(loginViewModel.auth.currentUser?.uid!!, cartItem)
            }
            CartAction.REMOVE -> {
                userViewModel.removeFromCart(loginViewModel.auth.currentUser?.uid!!, cartItem)
            }
        }
    }

}