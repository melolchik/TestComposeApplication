package com.example.testcomposeapplication.navigation

 sealed class Screen (
     val route : String
 ){
     object NewsFeed : Screen(ROUTE_NEWS_FEED)
     object Fav : Screen(ROUTE_FAV)
     object Profile : Screen(ROUTE_PROFILE)

     private companion object{
         const val ROUTE_NEWS_FEED = "news_feed"
         const val ROUTE_FAV = "fav"
         const val ROUTE_PROFILE = "profile"
     }
 }