package com.example.practice.screen

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.practice.pages.post.RecipePostsCard
import com.example.practice.viewmodel.Recipe
import com.example.practice.viewmodel.RecipeViewModel
import com.google.gson.Gson
import java.net.URLEncoder

@Composable
fun HomeScreen(
    innerPadding: PaddingValues,
    navController: NavController,
    viewModel: RecipeViewModel = viewModel()
) {
    val recipes by viewModel.recipes.observeAsState(emptyList())

    // 初次加载时拉取数据
    LaunchedEffect(Unit) {
        viewModel.fetchAllRecipes()
    }

    if (recipes.isEmpty()) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = innerPadding,
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
        ) {
            items(recipes) { recipe ->
                RecipePostsCard(recipe = recipe) {
                    val recipeJson = URLEncoder.encode(Gson().toJson(recipe), "UTF-8")
                    navController.navigate("recipeDetail/$recipeJson")
                }
            }
        }
    }
}

//package com.example.practice.screen
//
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.items
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import com.example.practice.pages.post.RecipePostsCard
//import com.example.practice.viewmodel.Recipe
//import com.example.practice.viewmodel.RecipeViewModel
//
//@Composable
//fun HomeScreen(
//    innerPadding: PaddingValues,
//    navController: NavController,
//    viewModel: RecipeViewModel = viewModel()
//) {
//    val recipes by viewModel.recipes.collectAsState(initial = emptyList())
//
//    // 初次加载时拉取数据
//    LaunchedEffect(Unit) {
//        viewModel.fetchAllRecipes()
//    }
//
//    if (recipes.isEmpty()) {
//        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
//    } else {
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(2),
//            contentPadding = innerPadding,
//            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp),
//            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
//        ) {
//            items(recipes) { recipe ->
//                RecipePostsCard(recipe = recipe) {
//                    // 点击跳转详情页（你需要设置对应的NavHost跳转）
//                    // navController.navigate("recipeDetail/${recipe.id}")
//                }
//            }
//        }
//    }
//}
//
////package com.example.practice.screen
////
////import androidx.compose.foundation.background
////import androidx.compose.foundation.layout.Arrangement
////import androidx.compose.foundation.layout.PaddingValues
////import androidx.compose.foundation.layout.fillMaxSize
////import androidx.compose.foundation.layout.padding
////import androidx.compose.foundation.lazy.grid.GridCells
////import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
////import androidx.compose.foundation.lazy.grid.items
////import androidx.compose.material3.Text
////import androidx.compose.runtime.Composable
////import androidx.compose.runtime.LaunchedEffect
////import androidx.compose.runtime.livedata.observeAsState
////import androidx.compose.ui.Modifier
////import androidx.compose.ui.graphics.Color
////import androidx.compose.ui.unit.dp
////import com.example.practice.pages.post.RecipePostsCard
////import com.example.practice.viewmodel.RecipeViewModel
////
////@Composable
////fun HomeScreen(viewModel: RecipeViewModel = viewModel()) {
////    val recipeList by viewModel.allRecipes.observeAsState(emptyList())
////
////    // 首次加载数据
////    LaunchedEffect(Unit) {
////        viewModel.fetchAllRecipes()
////    }
////
////    LazyVerticalGrid(
////        columns = GridCells.Fixed(2),
////        contentPadding = PaddingValues(16.dp),
////        verticalArrangement = Arrangement.spacedBy(16.dp),
////        horizontalArrangement = Arrangement.spacedBy(16.dp),
////    ) {
////        items(recipeList) { recipe ->
////            RecipePostsCard(recipe = recipe) {
////                // 你可以实现点击后跳转详情页逻辑
////            }
////        }
////    }
////}
//////fun HomeScreen(innerPadding: PaddingValues) {
//////    val postCount = 20 // 可以根据实际数据来源动态生成列表
//////    val postList = List(postCount) { it } // 生成简单编号列表用于展示多个 RecipePostsCard
//////
//////    LazyVerticalGrid(
//////        columns = GridCells.Fixed(2),
//////        modifier = Modifier
//////            .fillMaxSize()
//////            .background(Color.White)
//////            .padding(innerPadding),
//////        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
//////        verticalArrangement = Arrangement.spacedBy(16.dp),
//////        horizontalArrangement = Arrangement.spacedBy(16.dp)
//////    ) {
//////        items(postList) { _ ->
//////            RecipePostsCard()
//////        }
//////    }
//////}
