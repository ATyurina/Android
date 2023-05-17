package ru.cococo.netologytest.repository

import androidx.lifecycle.LiveData
import ru.cococo.netologytest.kot.Post

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun shared()
    fun viewed()
}
