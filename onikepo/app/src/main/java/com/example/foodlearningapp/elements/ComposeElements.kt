package com.example.foodlearningapp.elements


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.foodlearningapp.R

@Composable
fun UserProfile(
    boxDynamicHeight: Dp,
    boxDynamicWidth: Dp,
    profileUrl: Int
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val boxWidthRatio = boxDynamicWidth / screenWidth.value
    val boxHeightRatio = boxDynamicHeight / screenHeight.value
    val boxDynamicWidth = screenWidth * boxWidthRatio.value
    val boxDynamicHeight = screenHeight * boxHeightRatio.value

    Box(
        modifier = Modifier
            .height(boxDynamicHeight)
            .width(boxDynamicWidth)
            .background(color = Color(0xFFAFC888), shape = CircleShape)
    ) {
        Icon(
            modifier = Modifier
                .height(boxDynamicHeight - 32.dp)
                .width(boxDynamicWidth - 32.dp)
                .align(Alignment.Center),
            painter = rememberAsyncImagePainter(profileUrl),
            contentDescription = "Profile",
        )
    }
}



@Composable
fun FixedButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Button(
        modifier = modifier,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            if (isSelected) Color(0xFFAFC988) else Color(0xFFF7B474)
        ),
        enabled = true,
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.source_code_pro_regular)),
                fontWeight = FontWeight(400),
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        )
    }
}


@Composable
fun RecipeShootsCard(
    videoShoots: String
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val cardWidthRatio = 105f / screenWidth.value
    val cardHeightRatio = 105f / screenHeight.value
    val cardDynamicWidth = screenWidth * cardWidthRatio
    val cardDynamicHeight = screenHeight * cardHeightRatio

    Card(
        modifier = Modifier
            .height(cardDynamicHeight)
            .width(cardDynamicWidth)
            .background(color = Color(0xFFEFE7DC)),
    ) {
        Image(
            painter = rememberAsyncImagePainter(videoShoots),
            contentDescription = "Recipe Shoots",
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
fun UserCommentsCard(
    profileUrl: Int,
    userName: String,
    comment: String
) {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    val boxHeightRatio = 91 / screenHeight.value
    val boxDynamicHeight = screenHeight * boxHeightRatio

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .height(boxDynamicHeight)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            UserProfile(90.dp, 90.dp,profileUrl)
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Text(
                    text = userName,
                    modifier = Modifier
                        .background(
                            color = Color(0xFFEFE7DC),
                        )
                )
                Text(
                    text = comment
                )
            }

        }
    }
}

