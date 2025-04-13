package com.example.foodlearningapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Chat
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodlearningapp.data.CommentDto
import com.example.foodlearningapp.network.RetrofitClient
import com.example.foodlearningapp.ui.theme.FoodLearningAppTheme
import com.example.foodlearningapp.ui.theme.OrangeCustom
import kotlinx.coroutines.launch
import android.util.Log

@Composable
fun CommentsScreen(navController: NavController) {
    var comments by remember { mutableStateOf<List<CommentDto>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) { // Run once when the composable is created
        coroutineScope.launch {
            try {
                val response = RetrofitClient.apiService.getComments().execute()
                if (response.isSuccessful) {
                    comments = response.body() ?: emptyList()
                    loading = false
                } else {
                    error = "Failed to fetch comments: ${response.code()}"
                    loading = false
                    Log.e("CommentsScreen", "Failed to fetch comments: ${response.code()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                error = "Network error: ${e.message}"
                loading = false
                Log.e("CommentsScreen", "Network error: ${e.message}")
            }
        }
    }

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
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Comments & Mentions", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            if (loading) {
                CircularProgressIndicator() // Show loading indicator
            } else if (error != null) {
                Text("Error: $error") // Display error message
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(comments) { commentDto ->
                        CommentItem(commentDto = commentDto) // Directly pass commentDto
                    }
                }
            }
        }
    }
}

// Helper function to generate a random color for the preview
fun getRandomColor(): Color {
    return Color(
        red = kotlin.random.Random.nextFloat(),
        green = kotlin.random.Random.nextFloat(),
        blue = kotlin.random.Random.nextFloat(),
        alpha = 1.0f
    )
}

// Comment Item now accepts CommentDto
@Composable
fun CommentItem(commentDto: CommentDto) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(getRandomColor(), shape = CircleShape), // Using getRandomColor for UI
            contentAlignment = Alignment.Center
        ) {
            Text(text = commentDto.username.first().toString().uppercase(), color = Color.White)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(commentDto.username, fontWeight = FontWeight.Bold)
            Text(commentDto.message)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCommentsScreen() {
    FoodLearningAppTheme {
        CommentsScreen(navController = rememberNavController())
    }
}