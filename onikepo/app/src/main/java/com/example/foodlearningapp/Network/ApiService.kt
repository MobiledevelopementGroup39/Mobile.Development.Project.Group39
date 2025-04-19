package com.example.foodlearningapp.network

import com.example.foodlearningapp.data.PostResponse
import com.example.foodlearningapp.data.SearchResultDto

import com.example.foodlearningapp.data.TutorialPost
import com.example.foodlearningapp.data.CommentDto

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @POST("/api/tutorials") // The specific path of the API endpoint for creating tutorials
    fun createTutorial(@Body tutorialPost: TutorialPost): Call<PostResponse>
    @GET("/api/comments") // The endpoint path for fetching comments
    fun getComments(): Call<List<CommentDto>>
    @GET("/api/search") // The endpoint path for search
    fun searchFood(@Query("query") searchTerm: String): Call<List<
            SearchResultDto>>

}