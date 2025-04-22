package com.example.practice.screen

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.practice.R
import com.example.practice.viewmodel.Comments
import com.example.practice.elements.UserCommentsCard

@Composable
fun CommentScreen(innerPadding: PaddingValues) {
    var commentText by remember { mutableStateOf(TextFieldValue("")) }
    val commentList = remember { mutableStateListOf<Comments>() }

    Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
        Text(
            text = "Comments",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )


        // LazyColumn to show all comments
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(commentList.size) { index ->
                UserCommentsCard(
                    profileUrl = R.drawable.profile,
                    userName = commentList[index].userName,
                    comment = commentList[index].comment
                )
            }
        }

        // TextField to add new comment
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BasicTextField(
                value = commentText,
                onValueChange = { commentText = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(8.dp)
                    ) {
                        innerTextField()
                    }
                }
            )

            IconButton(
                onClick = {
                    // Add new comment when icon is clicked
                    commentList.add(Comments(R.drawable.profile, "New User", commentText.text))
                    commentText = TextFieldValue("") // Clear the input
                }
            ) {
                Icon(imageVector = Icons.Default.Send, contentDescription = "Send Comment")
            }
        }
    }
}
