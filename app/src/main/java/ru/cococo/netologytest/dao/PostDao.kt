package ru.cococo.netologytest.dao

import ru.cococo.netologytest.kot.Post

interface PostDao {
    fun getAll(): List<Post>
    fun save(post: Post): Post
    fun likeById(id: Long)
    fun removeById(id: Long)
    fun share(id: Long)
    fun view(id: Long)

}
