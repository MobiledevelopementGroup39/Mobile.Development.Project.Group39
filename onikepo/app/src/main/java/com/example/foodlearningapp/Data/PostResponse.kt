package com.example.foodlearningapp.data

data class PostResponse(
    val id: Int? = null,         // The ID of the newly created tutorial (nullable in case of errors)
    val message: String? = null,  // A success or error message from the backend
    val error: String? = null    // Optional field for specific error details
    // Add other fields from the backend response as needed
)