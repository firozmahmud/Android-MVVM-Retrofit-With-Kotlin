package com.example.myapplication.network

import com.example.myapplication.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIClient {

    private var retrofit: Retrofit? = null
    private val gson = GsonBuilder().setLenient().create()

    val client: Retrofit?
        get() {

            if (retrofit == null) {

                synchronized(Retrofit::class.java) {

                    if (retrofit == null) {

                        retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                            .client(getOkHttpClient())
                            .addConverterFactory(GsonConverterFactory.create(gson)).build()
                    }
                }
            }

            return retrofit
        }


    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
            .callTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS).build()
    }

}
