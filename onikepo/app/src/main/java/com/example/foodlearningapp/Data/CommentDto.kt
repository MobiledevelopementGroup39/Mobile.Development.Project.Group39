package com.example.foodlearningapp.data

data class CommentDto(
    val id: Int,
    val userId: Int,
    val username: String,
    val message: String,
    val createdAt: String

)