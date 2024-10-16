package com.example.testcomposeapplication.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.testcomposeapplication.R
import com.example.testcomposeapplication.navigation.Screen

sealed class NavigationItem(
    val screen :Screen,
    val titleResId: Int,
    val icon : ImageVector
) {

    object Home: NavigationItem(
        screen = Screen.NewsFeed,
        titleResId = R.string.nav_item_main,
        icon = Icons.Outlined.Home)
    object Fav: NavigationItem(
        screen = Screen.Fav,
        titleResId = R.string.nav_item_fav,
        icon = Icons.Outlined.Favorite)
    object Profile: NavigationItem(
        screen = Screen.Profile,
        titleResId = R.string.nav_item_profile,
        icon = Icons.Outlined.Person)
}