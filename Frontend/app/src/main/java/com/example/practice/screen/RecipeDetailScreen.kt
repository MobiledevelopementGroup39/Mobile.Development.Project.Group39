@file:OptIn(UnstableApi::class)

package com.example.practice.screen

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.practice.R
import com.example.practice.elements.RecipeShootsCard
import com.example.practice.elements.UserCommentsCard
import com.example.practice.viewmodel.Recipe
import com.google.gson.Gson
import java.net.URLDecoder
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.launch

@androidx.media3.common.util.UnstableApi
@Composable
fun RecipeDetailScreen(recipeJson: String?, innerPadding: PaddingValues, navController: NavController) {
    val decodedJson = recipeJson?.let { URLDecoder.decode(it, "UTF-8") }
    val recipe = decodedJson?.let { Gson().fromJson(it, Recipe::class.java) }
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    if (recipe == null) {
        Text(text = "Recipe data is missing.")
        return
    }

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(Uri.parse(recipe.videoUrl))
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = false
        }
    }

    DisposableEffect(Unit) {
        onDispose { exoPlayer.release() }
    }

    var selectedTab by remember { mutableStateOf("comments") }

    // 把整个页面放入 LazyColumn 中，确保滚动不会被遮挡
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        item {
            // 顶部返回 + 标题栏
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
                Text(
                    text = recipe.title,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        item {
            // 视频播放器
            AndroidView(
                factory = {
                    PlayerView(it).apply {
                        player = exoPlayer
                        useController = true
                        setShowRewindButton(true)
                        setShowFastForwardButton(true)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight / 4)
                    .padding(horizontal = 12.dp)
                    .background(Color(0xFFEFE7DC))
            )
        }


        item {
            val shootList = recipe.videoShoots.split(",").map { it.trim() }
            val lazyListState = rememberLazyListState()
            val coroutineScope = rememberCoroutineScope()

            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp.dp

            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .background(color = Color(0xFFEFE7DC)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LazyRow(
                    state = lazyListState,
                    userScrollEnabled = true,
                    modifier = Modifier.width(screenWidth - 64.dp),
                    contentPadding = PaddingValues(end = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // 封面图作为第一个元素
                    item {
                        Image(
                            painter = painterResource(id = recipe.imageRes),
                            contentDescription = null,
                            contentScale = ContentScale.Crop, // 让图片铺满容器
                            modifier = Modifier
                                .width(180.dp)
                                .height(120.dp)
                        )
                    }

                    // 后续 Shoots 图片
                    items(shootList.size) { index ->
                        RecipeShootsCard(videoShoots = shootList[index])
                    }
                }

                // 右箭头控制滚动
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            coroutineScope.launch {
                                val nextItem = lazyListState.firstVisibleItemIndex + 1
                                lazyListState.animateScrollToItem(nextItem)
                            }
                        }
                )
            }
        }

        item {
            // Tab row，先 Description 再 Comments
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Description",
                    modifier = Modifier
                        .clickable { selectedTab = "description" },
                    color = if (selectedTab == "description") Color.Black else Color.Gray
                )
                Text(
                    text = "Comments",
                    modifier = Modifier
                        .clickable { selectedTab = "comments" },
                    color = if (selectedTab == "comments") Color.Black else Color.Gray
                )
            }
        }

        when (selectedTab) {
            "comments" -> {
                items(5) {
                    UserCommentsCard(
                        profileUrl = R.drawable.profile,
                        userName = "User A",
                        comment = "Hi, how are you?"
                    )
                }
            }

            "description" -> {
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(end = 8.dp)
                        )
                        Text(text = "@${recipe.author}")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = recipe.description)
                }
            }
        }
    }
}

//package com.example.practice.screen
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import com.example.practice.R
//import com.example.practice.viewmodel.Recipe
//import com.google.gson.Gson
//import java.net.URLDecoder
//
//@Composable
//fun RecipeDetailScreen(recipeJson: String?, innerPadding: PaddingValues, navController: NavController) {
//    val decodedJson = recipeJson?.let { URLDecoder.decode(it, "UTF-8") }
//    val recipe = decodedJson?.let { Gson().fromJson(it, Recipe::class.java) }
//
//    if (recipe != null) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//        ) {
//            // return button
//            IconButton(onClick = { navController.popBackStack() }) {
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_back),
//                    contentDescription = "Back"
//                )
//            }
//
//            Text(text = "Title: ${recipe.title}")
//            Spacer(modifier = Modifier.height(8.dp))
//            Image(
//                painter = painterResource(id = recipe.imageRes),
//                contentDescription = null,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(200.dp),
//                contentScale = ContentScale.Crop
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(text = "Description: ${recipe.description}")
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(text = "Reactions: ${recipe.reactionCount}")
//            Text(text = "Videos: ${recipe.videoShoots}")
//        }
//    } else {
//        Text(text = "Recipe data is missing.")
//    }
//}
