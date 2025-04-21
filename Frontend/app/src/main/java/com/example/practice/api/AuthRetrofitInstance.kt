package com.example.practice.api

import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

    object AuthRetrofitInstance {

        private const val BASE_URL = "http://192.168.0.103:8000/"

        // Logging interceptor for debugging requests/responses
//        private val logging = HttpLoggingInterceptor().apply {
//            setLevel(HttpLoggingInterceptor.Level.BODY)
//        }
        private val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // OkHttpClient with logging
        private val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        // Retrofit builder
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        // Auth API service instance
        val api: AuthApiService by lazy {
            retrofit.create(AuthApiService::class.java)
        }
    }
