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
import com.example.foodlearningapp.ui.theme.FoodLearningAppTheme
import com.example.foodlearningapp.ui.theme.OrangeCustom

@Composable
fun CommentsScreen(navController: NavController) { // Accept NavController as a parameter
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
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(sampleComments) { comment ->
                    CommentItem(comment)
                }
            }
        }
    }
}

// Sample data
data class Comment(val username: String, val message: String, val color: Color)

val sampleComments = listOf(
    Comment("User A", "Hi, how are you?", Color.Green),
    Comment("User B", "Hello!", Color.Magenta),
    Comment("User C", "Interesting post.", Color.Blue),
    Comment("User D", "Agreed!", Color.Yellow),
    Comment("User E", "Thanks for sharing.", Color.Cyan),
    Comment("User F", "Cool!", Color.LightGray)
)

// Comment Item
@Composable
fun CommentItem(comment: Comment) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(comment.color, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(text = comment.username.first().toString().uppercase(), color = Color.White)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(comment.username, fontWeight = FontWeight.Bold)
            Text(comment.message)
        }
    }
}

// Removed the BottomNavigationBar here as it should be defined in a common location
// and receive the NavController from the parent composable (like MyApp in MainActivity).

@Preview(showBackground = true)
@Composable
fun PreviewCommentsScreen() {
    FoodLearningAppTheme {
        // Provide a dummy NavController for the preview
        CommentsScreen(navController = rememberNavController())
    }
}