package com.example.foodlearningapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodlearningapp.ui.theme.OrangeCustom
import com.example.foodlearningapp.ui.theme.FoodLearningAppTheme

@Composable
fun PostScreen(navController: NavController) { // Accept NavController as a parameter
    FoodLearningAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Follow") },
                    actions = {
                        IconButton(onClick = { navController.navigate("search") }) { // Navigate to "search" on click
                            Icon(Icons.Default.Search, contentDescription = "Search")
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { /* Open Drawer */ }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    backgroundColor = OrangeCustom
                )
            },
            bottomBar = {
                // You might have a BottomNavigationBar here in your actual layout
                // If so, ensure it also receives the navController
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Click + to publish your tutorial")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Color.LightGray)
                        .clickable { /* Upload Image Action */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Link, contentDescription = "Upload", tint = Color.Black)
                }
                Spacer(modifier = Modifier.height(35.dp))
                var title by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                var text by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Text") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPostScreen() {
    FoodLearningAppTheme {
        // Provide a dummy NavController for the preview
        PostScreen(navController = rememberNavController())
    }
}