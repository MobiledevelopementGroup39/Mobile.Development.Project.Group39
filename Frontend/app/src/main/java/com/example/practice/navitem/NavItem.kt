package com.example.practice.navitem


data class NavItem(
    val icon: Int,
    val badgeCount: Int? = null,
)

data class TopBarItem(
    val drawer : Int,
    val search : Int
)
