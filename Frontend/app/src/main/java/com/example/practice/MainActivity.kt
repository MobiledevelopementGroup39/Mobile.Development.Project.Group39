package com.example.practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practice.screen.HomeScreen
import com.example.practice.screen.ProfileScreen
import com.example.practice.screen.RecipeDetailScreen
//import com.example.practice.screen.TutorialScreen
import com.example.practice.ui.theme.PracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticeTheme {
                MyApp()
//                val navController = rememberNavController()
//
//                Scaffold { innerPadding ->
//                    Surface(
//                        modifier = Modifier.padding(innerPadding),
//                        color = MaterialTheme.colorScheme.background
//                    ) {
//                        NavHost(
//                            navController = navController,
//                            startDestination = Routes.HOME
//                        ) {
//
//                            composable(Routes.HOME) {
//                                HomeScreen(innerPadding = innerPadding, navController = navController)
//                            }
//                            composable(Routes.RECIPE_DETAIL) { backStackEntry ->
//                                val recipeJson = backStackEntry.arguments?.getString("recipeJson")
//                                RecipeDetailScreen(recipeJson = recipeJson)
//                            }
//                            composable(Routes.PROFILE) {
//                                ProfileScreen(innerPadding = innerPadding, navController = navController)
//                            }
////                            composable(Routes.TUTORIAL) {
////                                TutorialScreen(innerPadding = innerPadding)
////                            }
////                            composable(Routes.SEARCH) {
////                                // 搜索页面的实现
////                                SearchScreen(innerPadding = innerPadding)
////                            }
//
//                        }
//                    }
//                }
            }
        }
    }
}

//package com.example.practice
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MyApp()
//        }
//    }
//}
