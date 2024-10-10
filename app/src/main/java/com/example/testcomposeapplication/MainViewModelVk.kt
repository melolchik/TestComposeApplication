package com.example.testcomposeapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testcomposeapplication.domain.FeedPost
import com.example.testcomposeapplication.domain.StatisticItem

class MainViewModelVk :ViewModel(){
    private val _feedPost = MutableLiveData<FeedPost>(FeedPost())
    val feedPost :LiveData<FeedPost> = _feedPost

    fun updateCount(item : StatisticItem){
        val oldStatistics = feedPost.value?.statistics ?:throw IllegalStateException()
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if(oldItem.type == item.type){
                    oldItem.copy(count = oldItem.count + 1)
                } else{
                    oldItem
                }
            }
        }
        _feedPost.value = feedPost.value?.copy(statistics = newStatistics)
    }
}