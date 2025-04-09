package com.example.practice.screen


import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.MediaItem
import com.example.practice.elements.FixedButton
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.practice.R
import com.example.practice.elements.RecipeShootsCard
import com.example.practice.elements.UserCommentsCard
import com.example.practice.viewmodel.Comments
import com.example.practice.viewmodel.RecipeViewModel
import kotlinx.coroutines.launch

@Composable
fun RecipeTitle(title: String) {
    var selectedButton by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp


    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .background(color = Color(0xFFEFE7DC)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .clickable(
                    onClick = { }
                )
        )
        Spacer(modifier = Modifier.padding(start = screenWidth / 4))
        FixedButton(
            text = title,
            isSelected = true,
            onClick = { selectedButton },
            modifier = Modifier.wrapContentWidth()
        )
    }

}

@OptIn(UnstableApi::class)
@Composable
fun RecipeTutorial(videoUrl: String,viewModel: RecipeViewModel) {
    val context = LocalContext.current

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build()
    }
    LaunchedEffect(videoUrl) {
        videoUrl.let { videoUrl ->
            val mediaSource = MediaItem.fromUri(videoUrl)
            exoPlayer.setMediaItem(mediaSource)
            exoPlayer.prepare()
            exoPlayer.playWhenReady = false
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }
    // Fetch video URL from Firestore
    LaunchedEffect(Unit) {
        viewModel.fetchRecipe("Recipe")
    }

    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).apply {
                player = exoPlayer
                useController = true

                setShowRewindButton(true)
                setShowFastForwardButton(true)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .height(screenHeight / 4)
            .background(color = Color(0xFFEFE7DC))
    )

}

@Composable
fun RecipeShoots(videoShoots: String) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
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
            modifier = Modifier
                .width(screenWidth - 64.dp),
            contentPadding = PaddingValues(end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(15) { index ->
                RecipeShootsCard(videoShoots)
            }
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .clickable(
                    onClick = {
                        coroutineScope.launch {
                            val nextItem = (lazyListState.firstVisibleItemIndex + 1)
                            lazyListState.animateScrollToItem(nextItem)
                        }
                    }
                )
        )
    }
}

@Composable
fun CommentSection(comment: Comments) {

    var selectedButton by remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
                .background(color = Color(0xFFEFE7DC))
        ) {
            FixedButton(
                text = "Comments",
                isSelected = true,
                onClick = { selectedButton },
                modifier = Modifier.wrapContentWidth()
            )
        }

        LazyColumn {
            items(5) {
                UserCommentsCard(comment.profileUrl,comment.userName,comment.comment)
            }
        }
    }

}


@Composable
fun TutorialScreen(innerPaddingValues: PaddingValues) {

    val viewModel: RecipeViewModel = viewModel()
    val recipes by viewModel.recipe.observeAsState(null)

    val videoTitle = recipes?.title ?: "Null"
    val videoUrl = recipes?.videoUrl ?: "Null"
    val videoShoots = recipes?.videoShoots ?: "Null"
    val comment = Comments(
        profileUrl = R.drawable.profile,
        userName = "User A",
        comment = "Hi, how are you?"
    )

    Column(
        modifier = Modifier
            .padding(innerPaddingValues)
            .fillMaxSize()
    ) {
        RecipeTitle(videoTitle)
        RecipeTutorial(videoUrl,viewModel)
        RecipeShoots(videoShoots)
        CommentSection(comment)
    }

}
