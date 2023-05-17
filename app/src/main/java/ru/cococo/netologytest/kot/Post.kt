package ru.cococo.netologytest.kot

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likes: Int = 1999,
    val likedByMe: Boolean,
    val shared: Int = 3998,
    val viewed: Int = 1999999
)
