package com.example.foodlearningapp.ui.screens

import android.util.Log
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
import com.example.foodlearningapp.data.TutorialPost
import com.example.foodlearningapp.network.RetrofitClient
import com.example.foodlearningapp.ui.theme.FoodLearningAppTheme
import com.example.foodlearningapp.ui.theme.OrangeCustom
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun PostScreen(navController: NavController) {
    FoodLearningAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Follow") },
                    actions = {
                        IconButton(onClick = { navController.navigate("search") }) {
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
                // BottomNavigationBar (if you have one)
            }
        ) { paddingValues ->
            var title by remember { mutableStateOf("") }
            var text by remember { mutableStateOf("") }
            var uploadMessage by remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally // Center items horizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Click + to publish your tutorial")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Color.LightGray)
                        .clickable { /* Upload Image Action - Implement this later */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Link, contentDescription = "Upload", tint = Color.Black)
                }
                Spacer(modifier = Modifier.height(35.dp))
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Text") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    val tutorialPost = TutorialPost(title = title, text = text)
                    RetrofitClient.apiService.createTutorial(tutorialPost).enqueue(object : Callback<com.example.foodlearningapp.data.PostResponse> {
                        override fun onResponse(
                            call: Call<com.example.foodlearningapp.data.PostResponse>,
                            response: Response<com.example.foodlearningapp.data.PostResponse>
                        ) {
                            if (response.isSuccessful) {
                                val postResponse = response.body()
                                uploadMessage = postResponse?.message ?: "Tutorial uploaded successfully!"
                                title = "" // Clear input fields after successful post
                                text = ""
                                Log.d("PostScreen", "Upload successful: ${postResponse?.message}")
                            } else {
                                uploadMessage = "Failed to upload tutorial. Error: ${response.code()}"
                                Log.e("PostScreen", "Upload failed: ${response.code()} - ${response.errorBody()?.string()}")
                            }
                        }

                        override fun onFailure(call: Call<com.example.foodlearningapp.data.PostResponse>, t: Throwable) {
                            uploadMessage = "Network error: ${t.message}"
                            Log.e("PostScreen", "Network error: ${t.message}")
                        }
                    })
                }) {
                    Text("Publish Tutorial")
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (uploadMessage.isNotEmpty()) {
                    Text(uploadMessage)
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPostScreen() {
    FoodLearningAppTheme {
        PostScreen(navController = rememberNavController())
    }
}