package com.example.testcomposeapplication.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testcomposeapplication.MainViewModelVk
import com.example.testcomposeapplication.domain.FeedPost
import com.example.testcomposeapplication.ui.theme.TestComposeApplicationTheme



@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnrememberedMutableState", "RememberReturnType",
    "UnusedMaterial3ScaffoldPaddingParameter"
)
@Composable
fun MainScreen(viewModel: MainViewModelVk){


    Scaffold ( modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background),

        bottomBar = {
        NavigationBar  {
           val selectedItemPos = remember {
               mutableStateOf(0)
           };
           val items = listOf(NavigationItem.Home, NavigationItem.Fav, NavigationItem.Profile)
            items.forEachIndexed{index, item ->
                NavigationBarItem(selected = selectedItemPos.value == index,
                    onClick = {
                        selectedItemPos.value = index
                    },
                    label = { Text(text = stringResource(item.titleResId)) },
                    icon = {
                        Icon(item.icon, contentDescription = null)
                    },
                    colors = NavigationBarItemColors(selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                        selectedIndicatorColor = Color.Unspecified,
                        disabledIconColor = Color.Unspecified,
                        disabledTextColor = Color.Unspecified)
                )
            }
        }
    })
    {
        val feedPosts = viewModel.feedPosts.observeAsState(listOf())

        LazyColumn (contentPadding = PaddingValues(
            top = 16.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = 120.dp
        ),
            verticalArrangement = Arrangement.spacedBy(8.dp)){
            items(feedPosts.value, key = {it.id}){
                feedPost ->
                val dismissState = rememberSwipeToDismissBoxState()
                if(dismissState.currentValue == SwipeToDismissBoxValue.EndToStart){
                    viewModel.remove(feedPost)
                }
                SwipeToDismissBox(
                    modifier = Modifier.animateItemPlacement(),
                    state = dismissState,
                    enableDismissFromEndToStart = true,
                    enableDismissFromStartToEnd = false,
                    backgroundContent = {

                }) {

                    PostCard(modifier = Modifier, feedPost = feedPost,
                        onViewClickListener = { statisticItem ->
                            viewModel.updateCount(feedPost, statisticItem)
                        },
                        onLikeClickListener = { statisticItem ->
                            viewModel.updateCount(feedPost, statisticItem)
                        }, onShareClickListener = { statisticItem ->
                            viewModel.updateCount(feedPost, statisticItem)
                        }, onCommentClickListener = { statisticItem ->
                            viewModel.updateCount(feedPost, statisticItem)
                        })
                }
            }
        }

    }
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


