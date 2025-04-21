package com.example.practice.pages.post

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practice.R
import com.example.practice.viewmodel.Recipe

@Composable
fun RecipePostsCard(
    recipe: Recipe,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .background(color = Color.White)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Image(
                painter = painterResource(id = recipe.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = recipe.title,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.source_code_pro_regular)),
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = recipe.videoShoots,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFEFE7DC))
                .padding(6.dp),
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.source_code_pro_regular)),
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        )
    }
}

//package com.example.practice.pages.post
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Card
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.practice.R
//import com.example.practice.viewmodel.Recipe
//
//@Composable
//fun RecipePostsCard(recipe: Recipe, onClick: () -> Unit) {
//    Column(
//        modifier = Modifier
//            .wrapContentWidth()
//            .background(color = Color.White)
//            .clickable { onClick() },
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(180.dp)
//        ) {
//            Image(
//                painter = painterResource(id = recipe.imageRes),
//                contentDescription = null,
//                contentScale = ContentScale.Crop
//            )
//        }
//
//        Spacer(modifier = Modifier.height(4.dp))
//
//        Text(
//            text = recipe.title,
//            style = TextStyle(
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Bold,
//                fontFamily = FontFamily(Font(R.font.source_code_pro_regular)),
//                color = Color.Black,
//                textAlign = TextAlign.Center
//            )
//        )
//
//        Spacer(modifier = Modifier.height(2.dp))
//
//        Text(
//            text = recipe.videoShoots,
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(color = Color(0xFFEFE7DC))
//                .padding(6.dp),
//            style = TextStyle(
//                fontSize = 14.sp,
//                fontFamily = FontFamily(Font(R.font.source_code_pro_regular)),
//                fontWeight = FontWeight.Normal,
//                color = Color.Black,
//                textAlign = TextAlign.Center
//            )
//        )
//    }
//}
/////////////////////

//package com.example.practice.pages.post
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Card
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.practice.R
//import com.example.practice.viewmodel.Recipe
//
//@Composable
//fun RecipePostsCard(recipe: Recipe, onClick: () -> Unit) {
//    Column(
//        modifier = Modifier
//            .wrapContentWidth()
//            .background(color = Color.White)
//            .clickable { onClick() },
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(180.dp)
//        ) {
//            Image(
//                painter = painterResource(R.drawable.pizza), // 或者替换成你的 URL 或图片资源
//                contentDescription = null,
//                contentScale = ContentScale.Crop
//            )
//        }
//
//        Spacer(modifier = Modifier.height(4.dp))
//
//        Text(
//            text = recipe.title,
//            style = TextStyle(
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Bold,
//                fontFamily = FontFamily(Font(R.font.source_code_pro_regular)),
//                color = Color.Black,
//                textAlign = TextAlign.Center
//            )
//        )
//
//        Spacer(modifier = Modifier.height(2.dp))
//
//        Text(
//            text = recipe.videoShoots,
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(color = Color(0xFFEFE7DC))
//                .padding(6.dp),
//            style = TextStyle(
//                fontSize = 14.sp,
//                fontFamily = FontFamily(Font(R.font.source_code_pro_regular)),
//                fontWeight = FontWeight.Normal,
//                color = Color.Black,
//                textAlign = TextAlign.Center
//            )
//        )
//    }
//}
//
////package com.example.practice.pages.post
////
////import androidx.compose.foundation.Image
////import androidx.compose.foundation.background
////import androidx.compose.foundation.clickable
////import androidx.compose.foundation.layout.*
////import androidx.compose.material3.Card
////import androidx.compose.material3.Icon
////import androidx.compose.material3.Text
////import androidx.compose.runtime.Composable
////import androidx.compose.ui.Alignment
////import androidx.compose.ui.Modifier
////import androidx.compose.ui.graphics.Color
////import androidx.compose.ui.layout.ContentScale
////import androidx.compose.ui.res.painterResource
////import androidx.compose.ui.text.TextStyle
////import androidx.compose.ui.text.font.Font
////import androidx.compose.ui.text.font.FontFamily
////import androidx.compose.ui.text.font.FontWeight
////import androidx.compose.ui.text.style.TextAlign
////import androidx.compose.ui.unit.dp
////import androidx.compose.ui.unit.sp
////import androidx.navigation.NavController
////import com.example.practice.R
////import com.example.practice.viewmodel.Recipe
////
////@Composable
////fun RecipePostsCard(recipe: Recipe, onClick: () -> Unit) {
////    Column(
////        modifier = Modifier
////            .wrapContentWidth()
////            .background(color = Color.White)
////            .clickable { onClick() },
////        horizontalAlignment = Alignment.CenterHorizontally
////    ) {
////        Card(
////            modifier = Modifier
////                .fillMaxWidth()
////                .height(180.dp)
////        ) {
////            Image(
////                painter = painterResource(R.drawable.pizza), // 你可以用 Coil 来加载图片URL
////                contentDescription = null,
////                contentScale = ContentScale.Crop
////            )
////        }
////
////        Spacer(modifier = Modifier.height(4.dp))
////
////        Text(
////            text = recipe.title,
////            style = TextStyle(
////                fontSize = 16.sp,
////                fontWeight = FontWeight.Bold,
////                fontFamily = FontFamily(Font(R.font.source_code_pro_regular)),
////                color = Color.Black,
////                textAlign = TextAlign.Center
////            )
////        )
////
////        Spacer(modifier = Modifier.height(2.dp))
////
////        Text(
////            text = recipe.videoShoots,
////            modifier = Modifier
////                .fillMaxWidth()
////                .background(color = Color(0xFFEFE7DC))
////                .padding(6.dp),
////            style = TextStyle(
////                fontSize = 14.sp,
////                fontFamily = FontFamily(Font(R.font.source_code_pro_regular)),
////                fontWeight = FontWeight.Normal,
////                color = Color.Black,
////                textAlign = TextAlign.Center
////            )
////        )
////    }
////}
////
//////package com.example.practice.pages.post
//////
//////import androidx.compose.foundation.Image
//////import androidx.compose.foundation.background
//////import androidx.compose.foundation.layout.Arrangement
//////import androidx.compose.foundation.layout.Column
//////import androidx.compose.foundation.layout.Row
//////import androidx.compose.foundation.layout.Spacer
//////import androidx.compose.foundation.layout.fillMaxWidth
//////import androidx.compose.foundation.layout.height
//////import androidx.compose.foundation.layout.size
//////import androidx.compose.foundation.layout.wrapContentWidth
//////import androidx.compose.material3.Card
//////import androidx.compose.material3.Icon
//////import androidx.compose.material3.Text
//////import androidx.compose.runtime.Composable
//////import androidx.compose.ui.Alignment
//////import androidx.compose.ui.Modifier
//////import androidx.compose.ui.graphics.Color
//////import androidx.compose.ui.layout.ContentScale
//////import androidx.compose.ui.res.painterResource
//////import androidx.compose.ui.text.TextStyle
//////import androidx.compose.ui.text.font.Font
//////import androidx.compose.ui.text.font.FontFamily
//////import androidx.compose.ui.text.font.FontWeight
//////import androidx.compose.ui.text.style.TextAlign
//////import androidx.compose.ui.tooling.preview.Preview
//////import androidx.compose.ui.unit.dp
//////import androidx.compose.ui.unit.sp
//////import com.example.practice.R
//////
//////@Preview
//////@Composable
//////fun RecipePostsCard(recipe: Recipe, onClick: () -> Unit = {}) {
//////    Column(
//////        modifier = Modifier
//////            .wrapContentWidth()
//////            .background(color = Color.White)
//////            .clickable { onClick() },
//////        horizontalAlignment = Alignment.CenterHorizontally
//////    ) {
//////        Card(
//////            modifier = Modifier
//////                .fillMaxWidth()
//////                .height(180.dp)
//////        ) {
//////            Image(
//////                painter = painterResource(R.drawable.pizza),
//////                contentDescription = null,
//////                contentScale = ContentScale.Crop
//////            )
//////        }
//////        Spacer(modifier = Modifier.height(4.dp))
//////
//////        Text(
//////            text = "hot and numbing Chicken strips",
//////            style = TextStyle(
//////                fontSize = 16.sp,
//////                lineHeight = 28.sp,
//////                fontFamily = FontFamily(Font(R.font.source_code_pro_regular)),
//////                fontWeight = FontWeight(700),
//////                color = Color.Black,
//////                textAlign = TextAlign.Center,
//////            )
//////        )
//////        Spacer(modifier = Modifier.height(2.dp))
//////
//////        Text(
//////            text = recipe.comments,
//////            modifier = Modifier
//////                .fillMaxWidth()
//////                .background(color = Color(0xFFEFE7DC))
//////                .padding(6.dp),
//////            style = TextStyle(
//////                fontSize = 14.sp,
//////                fontFamily = FontFamily(Font(R.font.source_code_pro_regular)),
//////                fontWeight = FontWeight(400),
//////                color = Color.Black,
//////                textAlign = TextAlign.Center,
//////            )
//////        )
//////
////////        Row(
////////            modifier = Modifier.fillMaxWidth()
////////                .background(color = Color(0xFFEFE7DC))
////////        ) {
////////            Text(
////////                text = "This is a very simple gazpacho that is perfect for dinner!!",
////////
////////                // BODY
////////                style = TextStyle(
////////                    fontSize = 16.sp,
////////                    lineHeight = 20.sp,
////////                    fontFamily = FontFamily(Font(R.font.source_code_pro_regular)),
////////                    fontWeight = FontWeight(400),
////////                    color = Color(0xFF000000),
////////                    textAlign = TextAlign.Center,
////////                )
////////            )
////////            Spacer(modifier = Modifier.height(16.dp))
////////        }
////////        Row(
////////            modifier = Modifier.fillMaxWidth(),
////////            horizontalArrangement = Arrangement.SpaceAround
////////        ) {
////////            Text(
////////                text = "Gao",
////////                style = TextStyle(
////////                    fontSize = 16.sp,
////////                    lineHeight = 28.sp,
////////                    fontFamily = FontFamily(Font(R.font.source_code_pro_regular)),
////////                    fontWeight = FontWeight(700),
////////                    color = Color(0xFF000000),
////////                    textAlign = TextAlign.Center,
////////                )
////////            )
////////            Row{
////////                Icon(
////////                    modifier = Modifier.size(20.dp),
////////                    painter = painterResource(R.drawable.heart_reatc),
////////                    contentDescription = null
////////                )
////////                Spacer(modifier = Modifier.height(2.dp))
////////
////////                Text(
////////                    text = "27.2k",
////////                    style = TextStyle(
////////                        fontSize = 16.sp,
////////                        lineHeight = 20.sp,
////////                        fontFamily = FontFamily(Font(R.font.source_code_pro_regular)),
////////                        fontWeight = FontWeight(400),
////////                        color = Color(0xFF212121),
////////                        textAlign = TextAlign.Center,
////////                    )
////////                )
////////            }
////////        }
////////    }
////////}