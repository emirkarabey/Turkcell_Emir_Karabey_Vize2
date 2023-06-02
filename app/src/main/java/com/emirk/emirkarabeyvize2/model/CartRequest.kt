package com.emirk.emirkarabeyvize2.model

data class CartRequest(
    val userId: Int,
    val products: List<ProductRequest>
)