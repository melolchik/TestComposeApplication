package com.example.testcomposeapplication.domain

import com.example.testcomposeapplication.R

data class PostComment(
    val id: Int,
    val authorName : String = "Author",
    val authorAvatarId : Int = R.drawable.post_comunity_thumbnail,
    val commentText : String = "Long comment Text",
    val publicationDate : String = "14:00"
)
