package com.example.ecomuserbatch4.models

data class CartItem(
    var productId: String? = null,
    var productName: String? = null,
    var quantity: Int = 1,
    var price: Double? = null,
)
