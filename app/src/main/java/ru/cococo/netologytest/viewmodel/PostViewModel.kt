package ru.cococo.netologytest.viewmodel

import androidx.lifecycle.ViewModel
import ru.cococo.netologytest.kot.Post
import ru.cococo.netologytest.repository.PostRepository
import ru.cococo.netologytest.repository.PostRepositoryInMemoryImplementation

class PostViewModel: ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImplementation()

    val data = repository.getAll()

    fun likeById(id: Long) = repository.likeById(id)

    fun shared(id: Long) = repository.shared(id)

    fun viewed(id: Long) = repository.viewed(id)
}
