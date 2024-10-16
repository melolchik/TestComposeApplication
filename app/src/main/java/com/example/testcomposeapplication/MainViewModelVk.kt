package com.example.testcomposeapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testcomposeapplication.domain.FeedPost
import com.example.testcomposeapplication.domain.StatisticItem
import com.example.testcomposeapplication.ui.NavigationItem

class MainViewModelVk :ViewModel(){

    private val _sourceList = mutableListOf<FeedPost>().apply {
        repeat(20){
            add(FeedPost(id = it))
        }
    }
    private val _feedPosts = MutableLiveData<List<FeedPost>>(_sourceList)
    val feedPosts :LiveData<List<FeedPost>> = _feedPosts

    private val _selectedNavItem = MutableLiveData<NavigationItem>(NavigationItem.Home)

    val selectedNavItem :LiveData<NavigationItem> = _selectedNavItem

    fun selectNavItem(navigationItem: NavigationItem){
        _selectedNavItem.value = navigationItem;
    }
    fun updateCount(feedPost:FeedPost, item : StatisticItem){
        val oldPosts = feedPosts.value?.toMutableList() ?: mutableListOf()
        val oldStatistics = feedPost.statistics
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if(oldItem.type == item.type){
                    oldItem.copy(count = oldItem.count + 1)
                } else{
                    oldItem
                }
            }
        }
        val newFeedPost = feedPost.copy(statistics = newStatistics)
        _feedPosts.value = oldPosts.apply {
            replaceAll {
                if (it.id == feedPost.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }

    }

    fun remove(feedPost: FeedPost){
        val oldPosts = feedPosts.value?.toMutableList() ?: mutableListOf()
        oldPosts.remove(feedPost)
        _feedPosts.value = oldPosts
    }
}