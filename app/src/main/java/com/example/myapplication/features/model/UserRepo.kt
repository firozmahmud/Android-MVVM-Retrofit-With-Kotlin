package com.example.myapplication.features.model

import android.content.Context
import com.example.myapplication.common.RequestCompleteListener
import com.example.myapplication.features.model.data_class.UserResponse
import com.example.myapplication.network.APIClient
import com.example.myapplication.network.MyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepo(private val context: Context) : UserDataModel {

    override fun getUsers(callback: RequestCompleteListener<UserResponse>) {


        val call = APIClient.client?.create(MyApi::class.java)?.getUsers()

        call?.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {

                if (response.isSuccessful && response.body() != null) {

                    callback.onRequestSuccess(response.body()!!)
                } else {
                    callback.onRequestFailed(response.message())
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                callback.onRequestFailed(t.localizedMessage)
            }

        })

    }


}