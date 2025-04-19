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
import com.example.foodlearningapp.data.SearchResultDto
import com.example.foodlearningapp.network.RetrofitClient
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import android.util.Log

@Composable
fun SearchScreen() {
    var searchText by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf<List<SearchResultDto>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            label = { Text("Search for food") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            if (searchText.isNotBlank()) {
                loading = true
                error = null
                coroutineScope.launch {
                    try {
                        val response = RetrofitClient.apiService.searchFood(searchText).execute()
                        if (response.isSuccessful) {
                            searchResults = response.body() ?: emptyList()
                            loading = false
                        } else {
                            error = "Search failed: ${response.code()}"
                            loading = false
                            Log.e("SearchScreen", "Search failed: ${response.code()} - ${response.errorBody()?.string()}")
                        }
                    } catch (e: Exception) {
                        error = "Network error: ${e.message}"
                        loading = false
                        Log.e("SearchScreen", "Search error: ${e.message}")
                    }
                }
            }
        }) {
            Text("Search")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Chicken", modifier = Modifier.clickable { searchText = "Chicken" })
            Text("Pizza", modifier = Modifier.clickable { searchText = "Pizza" })
            Text("Burger", modifier = Modifier.clickable { searchText = "Burger" })
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text("History", style = MaterialTheme.typography.subtitle1)
        Spacer(modifier = Modifier.height(8.dp))
        Column {
            Text("Pasta", modifier = Modifier.clickable { searchText = "Pasta" })
            Text("Salad", modifier = Modifier.clickable { searchText = "Salad" })
            Text("Soup", modifier = Modifier.clickable { searchText = "Soup" })
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (loading) {
            CircularProgressIndicator()
        } else if (error != null) {
            Text("Error: $error")
        } else if (searchResults.isNotEmpty()) {
            Text("Search Results:", style = MaterialTheme.typography.subtitle1)
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                items(searchResults) { result ->
                    Text(result.title, modifier = Modifier.clickable { /* Handle result click */ })
                    if (result.description != null) {
                        Text(result.description, style = MaterialTheme.typography.caption)
                    }
                    Divider()
                }
            }
        } else if (searchText.isNotBlank()) {
            Text("No results found.")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}