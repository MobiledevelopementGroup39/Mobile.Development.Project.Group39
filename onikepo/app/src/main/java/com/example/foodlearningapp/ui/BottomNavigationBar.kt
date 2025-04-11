package com.example.foodlearningapp.ui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Chat
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue
import com.example.foodlearningapp.ui.theme.OrangeCustom
import androidx.compose.ui.res.painterResource


import com.example.foodlearningapp.R




@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        backgroundColor = OrangeCustom
    ) {
        BottomNavigationItem(
            icon = { Icon(painterResource(id = R.drawable.ic_home), contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentRoute == "home", // Update route if needed
            onClick = { navController.navigate("home") { // Update route if needed
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            } }
        )


        BottomNavigationItem(
            icon = { Icon(painterResource(id = R.drawable.ic_heart), contentDescription = "Favorites") },
            label = { Text("Favorites") },
            selected = false, // You might not have a direct "favorites" route selected initially
            onClick = {

                println("Favorite button clicked!")

            }
        )

        BottomNavigationItem(
            icon = { Icon(painterResource(id = R.drawable.ic_chat), contentDescription = "Comments") },
            label = { Text("Comments") },
            selected = currentRoute == "comments",
            onClick = {
                navController.navigate("comments") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Add, contentDescription = "Post") }, // Keeping your existing Add icon
            label = { Text("Post") },
            selected = currentRoute == "post",
            onClick = {
                navController.navigate("post") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        BottomNavigationItem(
            icon = { Icon(painterResource(id = R.drawable.ic_person), contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = currentRoute == "profile", // Update route if needed
            onClick = { navController.navigate("profile") { // Update route if needed
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            } }
        )
    }
}