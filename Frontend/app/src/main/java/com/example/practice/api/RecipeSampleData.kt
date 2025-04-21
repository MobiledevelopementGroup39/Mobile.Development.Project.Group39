package com.example.practice.api

import com.example.practice.R
import com.example.practice.viewmodel.Recipe

val allRecipeData = listOf(
    Recipe(
        id = "1",
        title = "Hot and Numbing Chicken strips",
        description = "This is a very simple gazpacho that is perfect for dinner!",
        imageRes = R.drawable.chicken_strips,
        videoShoots = "video · 10",
        reactionCount = "27k",
        videoUrl = "https://sample-videos.com/video123.mp4"
    ),
    Recipe(
        id = "2",
        title = "Mapo Tofu",
        description = "Super delicious spicy flavored tofu",
        imageRes = R.drawable.mapo_tofu,
        videoShoots = "video · 8",
        reactionCount = "13.5k",
        videoUrl = "https://sample-videos.com/video456.mp4"
    )
)

//package com.example.practice.api
//
//import com.example.practice.R
//import com.example.practice.model.Recipe
//
//val allRecipeData = listOf(
//    Recipe(
//        id = "1",
//        title = "Hot and Numbing Chicken strips",
//        description = "This is a very simple gazpacho that is perfect for dinner!",
//        imageResId = R.drawable.pizza,
//        author = "Ayomide",
//        reactionCount = "27.7k"
//    ),
//    Recipe(
//        id = "2",
//        title = "Mapo Tofu",
//        description = "Super delicious spicy flavored tofu",
//        imageResId = R.drawable.mapo_tofu,
//        author = "Gao",
//        reactionCount = "12.3k"
//    )
//)
