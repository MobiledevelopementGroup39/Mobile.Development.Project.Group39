//package com.example.practice.ui.components
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//
//@Composable
//
//fun RecipeShootsCard(videoShoots: String) {
//    val resId = videoShoots.toIntOrNull() ?: return
//
//    Card(
//        modifier = Modifier
//            .width(150.dp)
//            .height(100.dp),
//        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
//    ) {
//        Image(
//            painter = painterResource(id = imageRes),
//            contentDescription = null,
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//        )
//    }
//
//}
