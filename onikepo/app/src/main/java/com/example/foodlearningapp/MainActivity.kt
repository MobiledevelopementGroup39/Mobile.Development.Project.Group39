package com.example.foodlearningapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodlearningapp.ui.screens.CommentsScreen
import com.example.foodlearningapp.ui.screens.PostScreen
import com.example.foodlearningapp.ui.screens.SearchScreen
import com.example.foodlearningapp.ui.BottomNavigationBar
import com.example.foodlearningapp.ui.theme.FoodLearningAppTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController() // Navigation controller

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) } // Add Bottom Nav Bar
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavigationGraph(navController) // Handles screen switching
        }
    }
}


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "post") {
        composable("post") { PostScreen(navController = navController) } // Pass the navController
        composable("comments") { CommentsScreen(navController = navController) } // Ensure this is also updated
        composable("favorites") { /* Your Favorites Screen */ }
        composable("search") { SearchScreen() }
    }
}





