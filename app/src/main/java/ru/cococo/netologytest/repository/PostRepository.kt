package ru.cococo.netologytest.repository

import androidx.lifecycle.LiveData
import ru.cococo.netologytest.kot.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun share(id: Long)
    fun view(id: Long)

    fun removeById(id: Long)

    fun save(post: Post)



}
