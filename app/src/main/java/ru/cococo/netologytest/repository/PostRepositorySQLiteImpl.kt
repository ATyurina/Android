package ru.cococo.netologytest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.cococo.netologytest.dao.PostDao
import ru.cococo.netologytest.kot.Post

class PostRepositorySQLiteImpl(
    private val dao: PostDao
) : PostRepository {
    private var posts = emptyList<Post>()
    private val data = MutableLiveData(posts)

    init {
        posts = dao.getAll()
        data.value = posts
    }

    override fun getAll(): LiveData<List<Post>> = data

    override fun save(post: Post) {
        val id = post.id
        val saved = dao.save(post)
        posts = if (post.id == 0L) {
            listOf(saved) + posts
        } else {
            posts.map {
                if (it.id != id) it else saved
            }
        }
        data.value = posts
    }

    override fun likeById(id: Long) {
        dao.likeById(id)
        posts = posts.map {
            if (it.id != id) it else it.copy(
                likedByMe = !it.likedByMe,
                likes = if (it.likedByMe) it.likes - 1 else it.likes + 1
            )
        }
        data.value = posts
    }

    override fun share(id: Long) {
        dao.share(id)
        posts = posts.map {
            if (it.id != id) it else it.copy(share = it.share + 1)
        }
        data.value = posts
    }

    override fun view(id: Long) {
        dao.view(id)
        posts = posts.map {
            if (it.id != id) it else it.copy(view = it.view + 1)
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        dao.removeById(id)
        posts = posts.filter { it.id != id }
        data.value = posts
    }

}
