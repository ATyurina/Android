package ru.cococo.netologytest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.cococo.netologytest.kot.Post
import ru.cococo.netologytest.repository.PostRepository
import ru.cococo.netologytest.repository.PostRepositoryInMemoryImplementation

private val empty = Post (
    id = 0,
    author = "",
    content = "",
    published = "",
    likedByMe = false,
    isSharedByMe = false
)
class PostViewModel: ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImplementation()

    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun clear() {
        edited.value = empty
    }
    fun changeContentAndSave(content: String) {
        edited.value?.let {
            val text = content.trim()
            if (it.content != text) {
                repository.save(it.copy(content = text))
            }
        }
        clear()
    }


    fun edit(post: Post) {
        edited.value = post
    }
    fun likeById(id: Long) = repository.likeById(id)

    fun shared(id: Long) = repository.shared(id)

    fun viewed(id: Long) = repository.viewed(id)

    fun removeByTd(id: Long) = repository.removeById(id)

}
