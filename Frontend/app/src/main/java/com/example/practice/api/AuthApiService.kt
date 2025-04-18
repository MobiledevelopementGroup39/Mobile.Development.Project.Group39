package com.example.practice.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part

interface AuthApiService {

    @GET("api/profile/update/")
    suspend fun getUserInfo(@Header("Authorization") token: String): ProfileResponse

    // POST request to update user information
    @Multipart
    @PUT("api/profile/update/")
    suspend fun updateUserInfo(
        @Header("Authorization") token: String,
        @Part("username") username: RequestBody,
        @Part("email") email: RequestBody,
        @Part profile_picture: MultipartBody.Part? = null,
        @Part("UserId") userId: RequestBody? = null
    ): ProfileResponse


}