package com.example.practice

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.practice.navitem.NavItem
import com.example.practice.screen.HomeScreen
import com.example.practice.screen.ProfileScreen
//import com.example.practice.screen.TutorialScreen
import com.example.practice.viewmodel.Recipe
import com.example.practice.screen.RecipeDetailScreen
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder
import androidx.navigation.navArgument
import androidx.navigation.NavController
import com.example.practice.pages.post.PostScreen
import com.example.practice.screen.CommentScreen
import com.example.practice.screen.SearchScreen


@Composable
fun TopBar(
    navController: NavController,
    setSelectedIndex: (Int) -> Unit,
    onDrawerClick: () -> Unit = {},
    onExploreClick: () -> Unit = {},
    onFollowersClick: () -> Unit = {},
//    onSearchClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.drawer),
            contentDescription = "Drawer",
            modifier = Modifier
                .size(24.dp)
                .clickable { onDrawerClick() }
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Explore",
                modifier = Modifier.clickable { onExploreClick() }
            )
            Text(
                text = "Followers",
                modifier = Modifier.clickable { onFollowersClick() }
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.search),
            contentDescription = "Search",
            tint = Color.Unspecified,
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    setSelectedIndex(-1) // 取消选中任何底部按钮
                    navController.navigate(Routes.SEARCH)
                }
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
                        else Color(0xFFf9B77C),
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
    val navController = rememberNavController()
    var selectedIndex by remember { mutableIntStateOf(1) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopBar(
                        navController = navController,
                        setSelectedIndex = { selectedIndex = it },
                        onDrawerClick = { },
                        onExploreClick = { selectedIndex = 1 },
                        onFollowersClick = { selectedIndex = 4 },

                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(Color(0xFFf9B77C))
            )
        },
        bottomBar = {
            BottomBar(selectedIndex) { index ->
                selectedIndex = index
                when (index) {
                    1 -> {
                        navController.navigate(Routes.HOME) {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    2 -> {
                        navController.navigate(Routes.POST_SCREEN) {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                            launchSingleTop = true
                            restoreState = true


                    }
                    }
                    3 -> {
                        navController.navigate(Routes.COMMENT_SCREEN) {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    4 -> {
                        navController.navigate(Routes.PROFILE) {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
//                    3 -> {
//                        navController.navigate(Routes.TUTORIAL) {
//                            popUpTo(Routes.TUTORIAL) { inclusive = true }
//                            launchSingleTop = true
//                        }
//                    }
//                    4 -> {
//                        navController.navigate(Routes.PROFILE) {
//                            popUpTo(Routes.PROFILE) { inclusive = true }
//                            launchSingleTop = true
//                        }
//                    }
                    else -> {
                        // 其他情况
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME
        ) {
            composable(Routes.HOME) {
                ContentScreen(
                    selectedIndex = selectedIndex,
                    innerPadding = innerPadding,
                    navController = navController
                )
            }
            composable(Routes.SEARCH) {
                SearchScreen(innerPadding, navController)
            }

            composable(Routes.POST_SCREEN) { backStackEntry ->
                // 使用 innerPadding 来保证界面布局不会被底部导航栏遮挡
                val innerPadding = PaddingValues(0.dp) // 或者传递父级的 padding
                PostScreen(
                    innerPadding = innerPadding,
                    navController = navController,
                    onBack = {
                        navController.popBackStack() // 处理返回按钮
                    },
                    onPost = { title, content, videoUri, imageUri ->
                        // 发布逻辑
                        // 例如可以使用 viewModel 或直接提交数据
                    }
                )
            }


            composable(Routes.COMMENT_SCREEN) {
                CommentScreen(innerPadding)
            }


            composable(Routes.PROFILE) {
                ProfileScreen(innerPadding, navController)
            }


            composable(
                route = Routes.RECIPE_DETAIL,
                arguments = listOf(navArgument("recipeJson") { type = NavType.StringType })
            ) { backStackEntry ->
                val json = backStackEntry.arguments?.getString("recipeJson")?.let { URLDecoder.decode(it, "UTF-8") }
                val recipe = Gson().fromJson(json, Recipe::class.java)
                RecipeDetailScreen(recipeJson = json, innerPadding = innerPadding, navController = navController)
            }
        }
    }
}

@Composable
private fun ContentScreen(
    selectedIndex: Int,
    innerPadding: PaddingValues,
    navController: NavController
) {
    when (selectedIndex) {
        1 -> HomeScreen(innerPadding, navController)
//        3 -> TutorialScreen(innerPadding)
        4 -> ProfileScreen(innerPadding, navController)
    }
}

//package com.example.practice
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.example.practice.navitem.NavItem
//import com.example.practice.screen.HomeScreen
//import com.example.practice.screen.ProfileScreen
//import com.example.practice.screen.TutorialScreen
//
//@Composable
//fun TopBar(
//    onDrawerClick: () -> Unit = {},
//    onExploreClick: () -> Unit = {},
//    onFollowersClick: () -> Unit = {},
//    onSearchClick: () -> Unit = {}
//) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 16.dp, vertical = 10.dp),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Icon(
//            painter = painterResource(id = R.drawable.drawer),
//            contentDescription = "Drawer",
//            modifier = Modifier
//                .size(24.dp)
//                .clickable { onDrawerClick() }
//        )
//
//        Row(
//            horizontalArrangement = Arrangement.spacedBy(16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = "Explore",
//                modifier = Modifier.clickable { onExploreClick() }
//            )
//            Text(
//                text = "Followers",
//                modifier = Modifier.clickable { onFollowersClick() }
//            )
//        }
//
//        Icon(
//            painter = painterResource(id = R.drawable.search),
//            contentDescription = "Search",
//            tint = Color.Unspecified,
//            modifier = Modifier
//                .size(24.dp)
//                .clickable { onSearchClick() }
//        )
//    }
//}
//
//@Composable
//fun BottomBar(selectedIndex: Int, onItemSelected: (Int) -> Unit) {
//    val navItemList = listOf(
//        NavItem(icon = R.drawable.favorite, badgeCount = 0),
//        NavItem(icon = R.drawable.home, badgeCount = 1),
//        NavItem(icon = R.drawable.post, badgeCount = 2),
//        NavItem(icon = R.drawable.chat, badgeCount = 3),
//        NavItem(icon = R.drawable.profile, badgeCount = 4),
//    )
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(color = Color(0xFFF3ECEC))
//            .padding(8.dp),
//        horizontalArrangement = Arrangement.SpaceAround,
//        verticalAlignment = Alignment.CenterVertically,
//    ) {
//        navItemList.forEachIndexed { index, navItem ->
//            Box(
//                modifier = Modifier
//                    .size(52.dp)
//                    .background(
//                        color = if (selectedIndex == index) Color(0xFFB1CB90)
//                        else Color(0xFFf9B77C),
//                        shape = CircleShape
//                    )
//                    .clickable(
//                        interactionSource = remember { MutableInteractionSource() },
//                        indication = null
//                    ) {
//                        onItemSelected(index)
//                    },
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    painter = painterResource(id = navItem.icon),
//                    contentDescription = "Icon",
//                    tint = Color.Unspecified
//                )
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//
//@Composable
//fun MyApp() {
//    var selectedIndex by remember { mutableIntStateOf(1) }
//    val navController = rememberNavController()
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    TopBar(
//                        onDrawerClick = { /* TODO: 打开抽屉 */ },
//                        onExploreClick = { selectedIndex = 1 },
//                        onFollowersClick = { selectedIndex = 4 },
//                        onSearchClick = { selectedIndex = 5 } // 未来可以加 search 页面
//                    )
//                },
//                colors = TopAppBarDefaults.topAppBarColors(Color(0xFFf9B77C))
//            )
//        },
//        bottomBar = {
//            BottomBar(selectedIndex) { index -> selectedIndex = index }
//        }
//    ) { innerPadding ->
//        ContentScreen(selectedIndex, innerPadding, navController) // ✅ 传入 navController
//    }
//}
//
//@Composable
//private fun ContentScreen(
//    selectedIndex: Int,
//    innerPadding: PaddingValues,
//    navController: NavController // ✅ 接收 navController
//) {
//    when (selectedIndex) {
//        1 -> HomeScreen(innerPadding = innerPadding, navController = navController)
//        3 -> TutorialScreen(innerPadding)
//        4 -> ProfileScreen(innerPadding)
//    }
//}
//
////package com.example.practice
////
////import androidx.compose.foundation.background
////import androidx.compose.foundation.clickable
////import androidx.compose.foundation.interaction.MutableInteractionSource
////import androidx.compose.foundation.layout.Arrangement
////import androidx.compose.foundation.layout.Box
////import androidx.compose.foundation.layout.PaddingValues
////import androidx.compose.foundation.layout.Row
////import androidx.compose.foundation.layout.fillMaxWidth
////import androidx.compose.foundation.layout.padding
////import androidx.compose.foundation.layout.size
////import androidx.compose.foundation.shape.CircleShape
////import androidx.compose.material3.ExperimentalMaterial3Api
////import androidx.compose.material3.Icon
////import androidx.compose.material3.Scaffold
////import androidx.compose.material3.Text
////import androidx.compose.material3.TopAppBar
////import androidx.compose.material3.TopAppBarDefaults
////import androidx.compose.runtime.Composable
////import androidx.compose.runtime.getValue
////import androidx.compose.runtime.mutableIntStateOf
////import androidx.compose.runtime.remember
////import androidx.compose.runtime.setValue
////import androidx.compose.ui.Alignment
////import androidx.compose.ui.Modifier
////import androidx.compose.ui.graphics.Color
////import androidx.compose.ui.res.painterResource
////import androidx.compose.ui.unit.dp
////import androidx.navigation.compose.rememberNavController
////import com.example.practice.navitem.NavItem
////import com.example.practice.screen.ProfileScreen
////import com.example.practice.screen.TutorialScreen
////import com.example.practice.screen.HomeScreen
////
////@Composable
////fun TopBar(
////    onDrawerClick: () -> Unit = {},
////    onExploreClick: () -> Unit = {},
////    onFollowersClick: () -> Unit = {},
////    onSearchClick: () -> Unit = {}
////) {
////    Row(
////        modifier = Modifier
////            .fillMaxWidth()
////            .padding(horizontal = 16.dp, vertical = 10.dp),
////        horizontalArrangement = Arrangement.SpaceBetween,
////        verticalAlignment = Alignment.CenterVertically
////    ) {
////        // Drawer Icon
////        Icon(
////            painter = painterResource(id = R.drawable.drawer),
////            contentDescription = "Drawer",
////            modifier = Modifier
////                .size(24.dp)
////                .clickable { onDrawerClick() }
////        )
////
////        // Center texts
////        Row(
////            horizontalArrangement = Arrangement.spacedBy(16.dp),
////            verticalAlignment = Alignment.CenterVertically
////        ) {
////            Text(
////                text = "Explore",
////                modifier = Modifier.clickable { onExploreClick() }
////            )
////            Text(
////                text = "Followers",
////                modifier = Modifier.clickable { onFollowersClick() }
////            )
////        }
////
////        // Search Icon
////        Icon(
////            painter = painterResource(id = R.drawable.search),
////            contentDescription = "Search",
////            tint = Color.Unspecified,
////            modifier = Modifier
////                .size(24.dp)
////                .clickable { onSearchClick() }
////        )
////    }
////}
//////fun TopBar() {
//////    Row(
//////        modifier = Modifier.fillMaxWidth(),
//////        horizontalArrangement = Arrangement.SpaceBetween,
//////        verticalAlignment = Alignment.CenterVertically
//////    ) {
//////        Icon(
//////            painter = painterResource(id = R.drawable.drawer),
//////            contentDescription = "Drawer"
//////        )
//////        Text(text = "Explore")
//////        Text(text = "Followers") // New text item
//////        Icon(
//////            modifier = Modifier.padding(end = 10.dp),
//////            painter = painterResource(id = R.drawable.search),
//////            contentDescription = "Search",
//////            tint = Color.Unspecified
//////        )
//////    }
//////}
////
////@Composable
////fun BottomBar(selectedIndex: Int, onItemSelected: (Int) -> Unit) {
////
////    val navItemList = listOf(
////        NavItem(icon = R.drawable.favorite, badgeCount = 0),
////        NavItem(icon = R.drawable.home, badgeCount = 1),
////        NavItem(icon = R.drawable.post, badgeCount = 2),
////        NavItem(icon = R.drawable.chat, badgeCount = 3),
////        NavItem(icon = R.drawable.profile, badgeCount = 4),
////    )
////
////    Row(
////        modifier = Modifier
////            .fillMaxWidth()
////            .background(color = Color(0xFFF3ECEC))
////            .padding(8.dp),
////        horizontalArrangement = Arrangement.SpaceAround,
////        verticalAlignment = Alignment.CenterVertically,
////    ) {
////        navItemList.forEachIndexed { index, navItem ->
////            Box(
////                modifier = Modifier
////                    .size(52.dp)
////                    .background(
////                        color = if (selectedIndex == index) Color(0xFFB1CB90)
////                        else Color(0xFFf9B77C), // Unselected color
////                        shape = CircleShape
////                    )
////                    .clickable(
////                        interactionSource = remember { MutableInteractionSource() },
////                        indication = null
////                    ) {
////                        onItemSelected(index)
////                    },
////                contentAlignment = Alignment.Center
////            ) {
////
////                Icon(
////                    painter = painterResource(id = navItem.icon),
////                    contentDescription = "Icon",
////                    tint = Color.Unspecified
////                )
////
////            }
////        }
////    }
////
////}
////
////
////@OptIn(ExperimentalMaterial3Api::class)
////@Composable
////fun MyApp() {
////    var selectedIndex by remember { mutableIntStateOf(1) }
////    val navController = rememberNavController()
////
////    Scaffold(
////        topBar = {
////            TopAppBar(
////                title = {
////                    TopBar(
////                        onDrawerClick = { /* TODO: 打开抽屉 */ },
////                        onExploreClick = { selectedIndex = 1 },
////                        onFollowersClick = { selectedIndex = 4 },
////                        onSearchClick = { selectedIndex = 5 }// searchpage
////                    )
////                },
////                colors = TopAppBarDefaults.topAppBarColors(Color(0xFFf9B77C))
////            )
////        },
//////            TopAppBar(
//////                title = { TopBar() },
//////                colors = TopAppBarDefaults.topAppBarColors(Color(0xFFf9B77C))
//////            )
//////        },
////        bottomBar = { BottomBar(selectedIndex) { index -> selectedIndex = index } }
////
////    ){innerPadding ->
////        ContentScreen(selectedIndex, innerPadding, navController)
////    }
////}
////
////
////@Composable
////private fun ContentScreen(selectedIndex: Int,innerPadding: PaddingValues) {
////
////    when (selectedIndex) {
////        1 -> HomeScreen(innerPadding = innerPadding, navController = navController)
////        3 -> TutorialScreen(innerPadding)
////        4 -> ProfileScreen(innerPadding)
////    }
////}