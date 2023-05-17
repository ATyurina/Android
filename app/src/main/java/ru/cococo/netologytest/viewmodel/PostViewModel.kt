package ru.cococo.netologytest.viewmodel

import androidx.lifecycle.ViewModel
import ru.cococo.netologytest.repository.PostRepository
import ru.cococo.netologytest.repository.PostRepositoryInMemoryImplementation

class PostViewModel: ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImplementation()

    val data = repository.get()

    fun like() = repository.like()

    fun shared() = repository.shared()

    fun viewed() = repository.viewed()
}
