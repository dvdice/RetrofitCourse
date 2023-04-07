package com.example.retrofittest.retrofit

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MainAPI {
    @GET("products/{id}")
    suspend fun getProductByID(@Path("id") id: Int): Product

    @POST("auth/login")
    suspend fun Auth(@Body authRequest: AuthRequest): User

}