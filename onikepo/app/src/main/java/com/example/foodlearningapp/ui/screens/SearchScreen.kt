package com.example.foodlearningapp.ui.screens



import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = "",
            onValueChange = { /* Handle text input */ },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            label = { Text("Search for food") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Chicken", modifier = Modifier.clickable { /* Handle click */ })
            Text("Pizza", modifier = Modifier.clickable { /* Handle click */ })
            Text("Burger", modifier = Modifier.clickable { /* Handle click */ })
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text("History", style = MaterialTheme.typography.subtitle1)
        Spacer(modifier = Modifier.height(8.dp))
        Column {
            Text("Pasta", modifier = Modifier.clickable { /* Handle click */ })
            Text("Salad", modifier = Modifier.clickable { /* Handle click */ })
            Text("Soup", modifier = Modifier.clickable { /* Handle click */ })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}