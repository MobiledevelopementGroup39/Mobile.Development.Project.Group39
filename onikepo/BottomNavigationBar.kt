@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Add, contentDescription = "Post") },
            label = { Text("Post") },
            selected = false,
            onClick = { navController.navigate("post") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Chat, contentDescription = "Comments") },
            label = { Text("Comments") },
            selected = false,
            onClick = { navController.navigate("comments") }
        )
    }
}