package com.example.practice

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.practice.navitem.NavItem
import com.example.practice.screen.ProfileScreen
import com.example.practice.screen.TutorialScreen

@Composable
fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.drawer),
            contentDescription = "Drawer"
        )
        Text(text = "Explore")
        Text(text = "Followers") // New text item
        Icon(
            modifier = Modifier.padding(end = 10.dp),
            painter = painterResource(id = R.drawable.search),
            contentDescription = "Search",
            tint = Color.Unspecified
        )
    }
}

@Composable
fun BottomBar(selectedIndex: Int, onItemSelected: (Int) -> Unit) {

    val navItemList = listOf(
        NavItem(icon = R.drawable.favorite, badgeCount = 0),
        NavItem(icon = R.drawable.home, badgeCount = 1),
        NavItem(icon = R.drawable.post, badgeCount = 2),
        NavItem(icon = R.drawable.chat, badgeCount = 3),
        NavItem(icon = R.drawable.profile, badgeCount = 4),
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFF3ECEC))
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        navItemList.forEachIndexed { index, navItem ->
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .background(
                        color = if (selectedIndex == index) Color(0xFFB1CB90)
                        else Color(0xFFf9B77C), // Unselected color
                        shape = CircleShape
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        onItemSelected(index)
                    },
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    painter = painterResource(id = navItem.icon),
                    contentDescription = "Icon",
                    tint = Color.Unspecified
                )

            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    var selectedIndex by remember { mutableIntStateOf(3) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { TopBar() },
                colors = TopAppBarDefaults.topAppBarColors(Color(0xFFf9B77C))
            )
        },
        bottomBar = { BottomBar(selectedIndex) { index -> selectedIndex = index } }

    ){innerPadding ->
        ContentScreen(selectedIndex, innerPadding)
    }
}


@Composable
private fun ContentScreen(selectedIndex: Int,innerPadding: PaddingValues) {

    when (selectedIndex) {
        3 -> TutorialScreen(innerPadding)
        4 -> ProfileScreen(innerPadding)
    }
}

