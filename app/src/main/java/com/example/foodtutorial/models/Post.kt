package com.example.foodtutorial.models


data class Post(
    val title: String,
    val description: String,
    val userId: String,
    var likeCount: Int,
    val imageUrl: String = "https://images.pexels.com/photos/20943933/pexels-photo-20943933.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
    val avatarUrl: String = "https://images.pexels.com/photos/31457818/pexels-photo-31457818.jpeg?auto=compress&cs=tinysrgb&w=1200"
)