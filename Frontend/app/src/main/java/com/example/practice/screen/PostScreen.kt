package com.example.practice.pages.post

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.text.TextStyle
import androidx.compose.material3.TextField
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.practice.R // 引入资源包，确保可以访问到 drawable 文件夹中的资源
import com.example.practice.Routes

@Composable
fun PostScreen(
    innerPadding: PaddingValues,
    navController: NavController,
    onBack: () -> Unit,
    onPost: (String, String, Uri?, Uri?) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var videoUri by remember { mutableStateOf<Uri?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding) // Ensure padding is applied
            .padding(bottom = 70.dp), // 预留 BottomBar 空间
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "click + to publish your tutorial",
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )

        // 上传视频和图片框（竖向排列）
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 上传视频框
            Box(
                modifier = Modifier
                    .size(120.dp) // Increased size
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .clickable {
                        // TODO: 实现上传视频逻辑
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.link), // 引入 link.png 文件
                    contentDescription = "Upload Video",
                    modifier = Modifier.size(36.dp)  // 调整图标大小
                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // Add space between buttons

            // 上传图片框
            Box(
                modifier = Modifier
                    .size(120.dp) // Increased size
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .clickable {
                        // TODO: 实现上传图片逻辑
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Add, contentDescription = "Upload Image", tint = Color.Black)
            }
        }

        // 文字输入区域
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                // 标题输入框
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = {
                        Text(
                            "Title",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // 内容输入框
                TextField(
                    value = content,
                    onValueChange = { content = it },
                    placeholder = { Text("Text") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    maxLines = 5
                )
            }
        }

        // 底部按钮区
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 返回按钮
            Button(
                onClick = { navController.navigate(Routes.HOME) }, // Navigate to home screen
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                Text("Back", modifier = Modifier.padding(start = 4.dp))
            }

            // 发布按钮
            Button(
                onClick = {
                    // 使用 popBackStack() 返回上一个屏幕
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                Text("Back", modifier = Modifier.padding(start = 4.dp))
            }

//            Button(
//                onClick = { onPost(title, content, videoUri, imageUri) },
//                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF98C379))
//            ) {
//                Text("Post")
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostScreenPreview() {
    PostScreen(
        innerPadding = PaddingValues(0.dp),
        navController = rememberNavController(),
        onBack = {},
        onPost = { _, _, _, _ -> }
    )
}

//@Composable
//fun PostScreen(
//    innerPadding: PaddingValues,  // 新增 innerPadding
//    navController: NavController, // 新增 navController
//    onBack: () -> Unit,
//    onPost: (String, String, Uri?, Uri?) -> Unit
//) {
//    var title by remember { mutableStateOf("") }
//    var content by remember { mutableStateOf("") }
//    var videoUri by remember { mutableStateOf<Uri?>(null) }
//    var imageUri by remember { mutableStateOf<Uri?>(null) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(bottom = 70.dp), // 预留 BottomBar 空间
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Text(
//            text = "click + to publish your tutorial",
//            fontSize = 16.sp,
//            modifier = Modifier.padding(8.dp)
//        )
//
//        // 视频和图片上传框
//        Row(
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//            // 上传视频框
//            Box(
//                modifier = Modifier
//                    .size(100.dp)
//                    .background(Color.White, RoundedCornerShape(8.dp))
//                    .clickable {
//                        // TODO: 实现上传视频逻辑
//                    },
//                contentAlignment = Alignment.Center
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.link), // 引入 link.png 文件
//                    contentDescription = "Upload Video",
//                    modifier = Modifier.size(24.dp)  // 调整图标大小
//                )
//            }
//
//            // 上传图片框
//            Box(
//                modifier = Modifier
//                    .size(100.dp)
//                    .background(Color.White, RoundedCornerShape(8.dp))
//                    .clickable {
//                        // TODO: 实现上传图片逻辑
//                    },
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(Icons.Default.Add, contentDescription = "Upload Image", tint = Color.Black)
//            }
//        }
//
//        // 文字输入区域
//        Card(
//            shape = RoundedCornerShape(12.dp),
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth()
//                .background(Color.White)
//        ) {
//            Column(
//                modifier = Modifier
//                    .padding(16.dp)
//                    .fillMaxWidth()
//            ) {
//                // 标题输入框
//                TextField(
//                    value = title,
//                    onValueChange = { title = it },
//                    placeholder = {
//                        Text(
//                            "Title",
//                            textAlign = TextAlign.Center,
//                            modifier = Modifier.fillMaxWidth()
//                        )
//                    },
//                    textStyle = TextStyle(
//                        textAlign = TextAlign.Center,
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 18.sp
//                    ),
//                    modifier = Modifier.fillMaxWidth(),
//                    singleLine = true
//                )
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                // 内容输入框
//                TextField(
//                    value = content,
//                    onValueChange = { content = it },
//                    placeholder = { Text("Text") },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(100.dp),
//                    maxLines = 5
//                )
//            }
//        }
//
//        // 底部按钮区
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 24.dp, vertical = 8.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            // 返回按钮
//            Button(
//                onClick = onBack,
//                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)) {
//                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
//                Text("Back", modifier = Modifier.padding(start = 4.dp))
//            }
//
//            // 发布按钮
//            Button(
//                onClick = { onPost(title, content, videoUri, imageUri) },
//                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF98C379))
//            ) {
//                Text("Post")
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PostScreenPreview() {
//    val navController = rememberNavController()  // 创建一个 NavController 的模拟实例
//    val innerPadding = PaddingValues(0.dp)      // 可以使用默认的 PaddingValues
//
//    PostScreen(
//        innerPadding = innerPadding,
//        navController = navController,
//        onBack = { /* 模拟返回逻辑 */ },
//        onPost = { _, _, _, _ -> /* 模拟发布逻辑 */ }
//    )
//}
