package com.example.homework2.Retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitConfig {
    fun getRetrofit() = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/")
        .client( OkHttpClient.Builder()
            .connectTimeout(50, TimeUnit.SECONDS) // Adjust timeout values as needed
            .readTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}