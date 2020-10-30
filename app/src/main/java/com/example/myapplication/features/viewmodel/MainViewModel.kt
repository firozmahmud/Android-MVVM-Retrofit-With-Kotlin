package com.example.myapplication.features.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.common.RequestCompleteListener
import com.example.myapplication.features.model.UserRepo
import com.example.myapplication.features.model.data_class.User
import com.example.myapplication.features.model.data_class.UserNameList
import com.example.myapplication.features.model.data_class.UserResponse

class MainViewModel : ViewModel() {

    val progressLiveData = MutableLiveData<Boolean>()
    val userInfoSuccess = MutableLiveData<List<UserNameList>>()
    val userInfoFailed = MutableLiveData<String>()


    fun getUsers(model: UserRepo) {

        progressLiveData.postValue(true)

        model.getUsers(object : RequestCompleteListener<UserResponse> {
            override fun onRequestSuccess(data: UserResponse) {

                val userNameList = mutableListOf<UserNameList>()

                for (user in data.users) {
                    val userName = UserNameList()
                    userName.name = user.name
                    userNameList.add(userName)
                }

                userInfoSuccess.postValue(userNameList)
                progressLiveData.postValue(false)
            }

            override fun onRequestFailed(errorMessage: String) {
                progressLiveData.postValue(false)
                userInfoFailed.postValue(errorMessage)
            }

        })
    }

}