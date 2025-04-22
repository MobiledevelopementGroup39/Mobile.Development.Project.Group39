@file:OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)  // 加上这个

package com.example.practice.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practice.R

@Composable
fun SearchScreen(innerPadding: PaddingValues, navController: NavController) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val searchHistory = remember { mutableStateListOf("Pasta", "Salad", "Smoothie") }

    val suggestions = listOf("Pasta", "Pancake", "Pizza", "Salad", "Soup")
        .filter { it.contains(searchQuery.text, ignoreCase = true) && searchQuery.text.isNotBlank() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp)
    ) {
        // 添加返回按钮
        TopAppBar(
            title = { Text("Back") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back), // 替换成你自己的返回图标
                        contentDescription = "Back"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(Color(0xFFf9B77C))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 搜索框
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search recipes...") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 关键词建议
        if (suggestions.isNotEmpty()) {
            Text("Suggestions:", fontSize = 18.sp)
            LazyColumn {
                items(suggestions) { item ->
                    Text(
                        text = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                searchQuery = TextFieldValue(item)
                                if (!searchHistory.contains(item)) {
                                    searchHistory.add(0, item)
                                }
                            }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 搜索历史
        Text("Search History:", fontSize = 18.sp)
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            searchHistory.forEach { item ->
                Box(
                    modifier = Modifier
                        .background(Color(0xFFD0E8CF), RoundedCornerShape(16.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .clickable {
                            searchQuery = TextFieldValue(item)
                        }
                ) {
                    Text(text = item)
                }
            }
        }
    }
}

//@file:OptIn(ExperimentalLayoutApi::class)
//package com.example.practice.screen
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.input.TextFieldValue
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//@Composable
//fun SearchScreen(innerPadding: PaddingValues) {
//    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
//    val searchHistory = remember { mutableStateListOf("Pasta", "Salad", "Smoothie") }
//
//    val suggestions = listOf("Pasta", "Pancake", "Pizza", "Salad", "Soup")
//        .filter { it.contains(searchQuery.text, ignoreCase = true) && searchQuery.text.isNotBlank() }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(innerPadding)
//            .padding(16.dp)
//    ) {
//        // 搜索框
//        OutlinedTextField(
//            value = searchQuery,
//            onValueChange = { searchQuery = it },
//            placeholder = { Text("Search recipes...") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // 关键词建议
//        if (suggestions.isNotEmpty()) {
//            Text("Suggestions:", fontSize = 18.sp)
//            LazyColumn {
//                items(suggestions) { item ->
//                    Text(
//                        text = item,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(8.dp)
//                            .clickable {
//                                searchQuery = TextFieldValue(item)
//                                if (!searchHistory.contains(item)) {
//                                    searchHistory.add(0, item)
//                                }
//                            }
//                    )
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // 搜索历史
//        Text("Search History:", fontSize = 18.sp)
//        FlowRow(
//            horizontalArrangement = Arrangement.spacedBy(8.dp),
//            verticalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            searchHistory.forEach { item ->
//                Box(
//                    modifier = Modifier
//                        .background(Color(0xFFD0E8CF), RoundedCornerShape(16.dp))
//                        .padding(horizontal = 12.dp, vertical = 8.dp)
//                        .clickable {
//                            searchQuery = TextFieldValue(item)
//                        }
//                ) {
//                    Text(text = item)
//                }
//            }
//        }
//    }
//}
