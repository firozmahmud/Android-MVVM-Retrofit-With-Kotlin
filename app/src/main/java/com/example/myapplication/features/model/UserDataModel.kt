package com.example.myapplication.features.model

import com.example.myapplication.common.RequestCompleteListener
import com.example.myapplication.features.model.data_class.UserResponse

interface UserDataModel {
    fun getUsers(callback : RequestCompleteListener<UserResponse>)
}