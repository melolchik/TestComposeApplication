package com.example.testcomposeapplication.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue

import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.testcomposeapplication.MainViewModelVk
import com.example.testcomposeapplication.domain.FeedPost
import com.example.testcomposeapplication.navigation.AppNavGraph
import com.example.testcomposeapplication.ui.theme.TestComposeApplicationTheme


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint(
    "UnrememberedMutableState", "RememberReturnType",
    "UnusedMaterial3ScaffoldPaddingParameter"
)
@Composable
fun MainScreen(viewModel: MainViewModelVk) {

    //val selectedNavItem by viewModel.selectedNavItem.observeAsState(NavigationItem.Home)
    val navHostController = rememberNavController()

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background),

        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val items = listOf(NavigationItem.Home, NavigationItem.Fav, NavigationItem.Profile)
                items.forEach { item ->
                    NavigationBarItem(
                        //selected = selectedNavItem == item,
                        selected = currentRoute == item.screen.route,
                        onClick = {
                            //viewModel.selectNavItem(item)
                                  navHostController.navigate(item.screen.route)
                        },
                        label = { Text(text = stringResource(item.titleResId)) },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        colors = NavigationBarItemColors(
                            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                            selectedIndicatorColor = Color.Unspecified,
                            disabledIconColor = Color.Unspecified,
                            disabledTextColor = Color.Unspecified
                        )
                    )
                }
            }
        })
    {
        AppNavGraph(
            navHostController = navHostController,
            homeScreenContent = { HomeScreen(
                viewModel = viewModel, paddingValues = PaddingValues(
                    top = 16.dp,
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 120.dp
                )
            ) },
            favScreenContent = { TextCounter(name = "Fav")},
            profileScreenContent = {TextCounter(name = "Profile")})

//        when (selectedNavItem) {
//            NavigationItem.Home -> {
//
//            }
//
//            NavigationItem.Fav -> {
//
//            }
//
//            NavigationItem.Profile -> {
//                TextCounter(name = "Profile")
//            }
//
//        }


    }
}

@Composable
private fun TextCounter(name : String){
    var count = remember {
        mutableIntStateOf(0)
    }
    Text(
        modifier = Modifier
            .clickable { count.value++ }
            .padding(20.dp)
            .fillMaxSize(),
        text = "$name Count: ${count.value}",
        color = MaterialTheme.colorScheme.onSecondary)
}

//@Preview
//@Composable
//fun previewMainLight(){
//
//    TestComposeApplicationTheme(darkTheme = false) {
//        MainScreen()
//    }
//}
//
//@Preview
//@Composable
//fun previewMainDark(){
//    TestComposeApplicationTheme(darkTheme = true) {
//        MainScreen()
//    }
//}


