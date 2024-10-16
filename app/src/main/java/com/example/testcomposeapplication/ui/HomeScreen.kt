package com.example.testcomposeapplication.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testcomposeapplication.MainViewModelVk

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(viewModel: MainViewModelVk,
               paddingValues: PaddingValues){

    val feedPosts = viewModel.feedPosts.observeAsState(listOf())

    LazyColumn (contentPadding = paddingValues,
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