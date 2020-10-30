package com.example.myapplication.features.model.data_class

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @SerializedName("status")
    val status: String = "",
    @SerializedName("code")
    val code: Int = 0,
    @SerializedName("message")
    val message: String,
    @SerializedName("users")
    val users: List<User> = ArrayList()
)
