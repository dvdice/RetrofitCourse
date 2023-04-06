package com.example.retrofittest.retrofit

import retrofit2.http.GET
import retrofit2.http.Path

interface ProductAPI {
    @GET("products/{id}")
    suspend fun getProductByID(@Path("id") id: Int):Product
}