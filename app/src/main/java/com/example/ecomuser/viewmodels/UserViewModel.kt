package com.example.ecomuserbatch4.viewmodels

import androidx.lifecycle.ViewModel
import com.example.ecomuserbatch4.models.CartItem
import com.example.ecomuserbatch4.repos.UserRepository

class UserViewModel : ViewModel() {
    val userRepository = UserRepository()

    fun getAllCartItems(userId: String) = userRepository.getAllCartItems(userId)
    fun addToCart(userId: String, cartItem: CartItem) = userRepository.addToCart(userId, cartItem)
    fun removeFromCart(userId: String, cartItem: CartItem) = userRepository.removeFromCart(userId, cartItem)
}