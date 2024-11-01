package com.example.testcomposeapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph (navHostController: NavHostController,
                 homeScreenContent: @Composable () -> Unit,
                 favScreenContent: @Composable () -> Unit,
                 profileScreenContent: @Composable () -> Unit){
    NavHost(
        navController = navHostController,
        startDestination = Screen.NewsFeed.route){
        //new destination
        composable(Screen.NewsFeed.route){
            homeScreenContent()
        }

        composable(Screen.Fav.route){
            favScreenContent()
        }
        composable(Screen.Profile.route){
            profileScreenContent()
        }
    }
}