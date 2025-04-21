package com.example.practice.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.practice.R
import com.example.practice.viewmodel.Recipe
import com.google.gson.Gson
import java.net.URLDecoder

@Composable
fun RecipeDetailScreen(recipeJson: String?, innerPadding: PaddingValues, navController: NavController) {
    val decodedJson = recipeJson?.let { URLDecoder.decode(it, "UTF-8") }
    val recipe = decodedJson?.let { Gson().fromJson(it, Recipe::class.java) }

    if (recipe != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // return button
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back"
                )
            }

            Text(text = "Title: ${recipe.title}")
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = recipe.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Description: ${recipe.description}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Reactions: ${recipe.reactionCount}")
            Text(text = "Videos: ${recipe.videoShoots}")
        }
    } else {
        Text(text = "Recipe data is missing.")
    }
}
