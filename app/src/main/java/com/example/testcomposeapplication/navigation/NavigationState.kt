package com.example.testcomposeapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class NavigationState(
    val navHostController: NavHostController
) {

    fun navigateTo(route : String){
        navHostController.navigate(route){
            popUpTo(navHostController.graph.startDestinationId){
                saveState = true
            }
            //один экран в бэкстеке в топе -
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
fun rememberNavigationState (navHostController: NavHostController = rememberNavController()) : NavigationState{
    return remember {
        NavigationState(navHostController)
    }
}