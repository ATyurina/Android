package ru.cococo.netologytest.kot

import android.net.Uri

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likes: Int = 0,
    val likedByMe: Boolean,
    val share: Int = 0,
    val viewed: Int = 0,
    val video: Uri? = null
)
