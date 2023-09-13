package ru.cococo.netologytest.kot

import android.net.Uri

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likes: Int = 1999,
    val likedByMe: Boolean,
    val shared: Int = 99,
    val isSharedByMe: Boolean,
    val viewed: Int = 1999999,
    val video: Uri? = null
)

