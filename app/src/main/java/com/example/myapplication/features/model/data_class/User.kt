package com.example.myapplication.features.model.data_class

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name")
    val name: String,
    @SerializedName("area")
    val area: String
)