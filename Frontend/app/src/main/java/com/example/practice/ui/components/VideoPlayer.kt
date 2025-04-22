package com.example.practice.ui.components

import android.net.Uri
import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.unit.dp

@Composable
fun VideoPlayer(url: String) {
    AndroidView(
        factory = { context ->
            VideoView(context).apply {
                setVideoURI(Uri.parse(url))
                setMediaController(MediaController(context).apply {
                    setAnchorView(this@apply)
                })
                start()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}
