package com.example.testcomposeapplication.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.testcomposeapplication.R

sealed class NavigationItem(
    val titleResId: Int,
    val icon : ImageVector
) {

    object Home: NavigationItem(R.string.nav_item_main,icon = Icons.Outlined.Home)
    object Fav: NavigationItem(R.string.nav_item_fav,icon = Icons.Outlined.Favorite)
    object Profile: NavigationItem(R.string.nav_item_profile,icon = Icons.Outlined.Person)
}