package com.example.myapplication.network

import com.example.myapplication.features.model.data_class.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface MyApi {

    @GET("764cf2fd3af7cb46db63")
    fun getUsers(): Call<UserResponse>

}