package com.example.practice.viewmodel

data class Recipe(
    val id: String = "",
    val title: String = "",
    val description: String = "",
//    val imageRes: String = "",
    val imageRes: Int = 0,  // 用本地 drawable 图片资源 ID
//    val reactionCount: Int = 0,
    val reactionCount: String = "",
    val videoUrl: String = "",
    val videoShoots: String = ""
)
//data class Recipe(
//    val id: Int,
//    val title: String,
//    val description: String,
//    val imageRes: Int,
//    val videoShoots: String,
//    val reactionCount: String,
//    val videoUrl: String = ""
//)

//data class Recipe(
//    val id: String,
//    val title: String,
//    val description: String,
//    val imageResId: Int,
//    val author: String,
//    val reactionCount: String
//)
//data class Recipe(
//    val id: String = "",
//    val title: String = "",
//    val videoUrl: String = "",
//    val videoShoots: String = "",
//    val comments: String = ""
//)