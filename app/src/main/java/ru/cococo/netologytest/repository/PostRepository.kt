package ru.cococo.netologytest.repository

import androidx.lifecycle.LiveData
import ru.cococo.netologytest.kot.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun shared(id: Long)
    fun viewed(id: Long)
}
