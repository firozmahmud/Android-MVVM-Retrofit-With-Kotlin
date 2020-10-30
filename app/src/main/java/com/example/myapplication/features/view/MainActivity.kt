package com.example.myapplication.features.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import com.example.myapplication.common.BaseActivity
import com.example.myapplication.features.model.UserRepo
import com.example.myapplication.features.model.data_class.UserNameList
import com.example.myapplication.features.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    private lateinit var model: UserRepo
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = UserRepo(applicationContext)// we should not use model reference inside view
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        setLiveDataListener()
        setViewClickListener()
    }

    private fun setLiveDataListener() {

        viewModel.progressLiveData.observe(this, Observer {
            setProgressBar(it)
        })

        viewModel.userInfoSuccess.observe(this, Observer {
            updateUI(it)
        })

        viewModel.userInfoFailed.observe(this, Observer {
            showErrorMessage(it)
        })
    }


    private fun setViewClickListener() {

        btnGetUsers.setOnClickListener {
            viewModel.getUsers(model)
        }
    }

    private fun updateUI(userNameList: List<UserNameList>?) {

        val nameList = StringBuffer()

        if (userNameList != null) {

            val iterator = userNameList.iterator()

            for ((index, name) in iterator.withIndex()) {
                nameList.append("${index + 1} : ${name.name}\n")
            }

        }

        tvData.text = nameList

        showToast("Success")
    }


    private fun showErrorMessage(errorMessage: String?) {
        tvData.text = ""

        showToast(errorMessage)
    }

    private fun setProgressBar(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}