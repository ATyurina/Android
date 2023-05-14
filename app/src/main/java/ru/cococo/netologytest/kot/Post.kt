package ru.cococo.netologytest.kot

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likes: Int = 1999,
    var likedByMe: Boolean,
    var shared: Int = 0,
    var viewed: Int = 0
)
