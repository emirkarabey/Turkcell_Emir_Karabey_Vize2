package com.emirk.emirkarabeyvize2.service

import com.emirk.emirkarabeyvize2.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface DummyJsonService {

    @POST("/auth/login")
    fun login(
        @Body JWTUser: JWTUser
    ): Call<JWTUser>

    @GET("/products")
    fun getProducts(
    ): Call<Products>

    @GET("/products/1")
    fun getProductDetail(
    ): Call<ProductDetail>

    @Headers("Content-Type: application/json")
    @POST("carts/add")
    suspend fun addToCart(@Body cartRequest: CartRequest)

    @GET("/carts/1")
    fun getCart(
    ): Call<Cart>
}