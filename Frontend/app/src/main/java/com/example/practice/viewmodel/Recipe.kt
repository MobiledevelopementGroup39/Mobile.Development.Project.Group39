package com.example.practice.viewmodel

data class Recipe(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val imageRes: Int = 0, // 封面图 drawable 资源 ID
    val reactionCount: String = "",
    val videoUrl: String = "",
    val videoShoots: String = "", // 图片名逗号分隔，如 "step1,step2"
    val author: String = "",
    val avatarRes: Int = 0 // 作者头像资源 ID
)
//data class Recipe(
//    val id: String = "",
//    val title: String = "",
//    val description: String = "",
////    val imageRes: String = "",
//    val imageRes: Int = 0,  // local drawable pictures ID
////    val reactionCount: Int = 0,
//    val reactionCount: String = "",
//    val videoUrl: String = "",
//    val videoShoots: String = ""
//)
