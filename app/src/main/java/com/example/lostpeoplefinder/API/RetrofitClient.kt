package com.example.lostpeoplefinder.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  RetrofitClient {
    private const val BASE_URL = "http://10.0.0.212:5000/"

    val instance: APIServies by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(APIServies::class.java)
    }
}