package ru.cococo.netologytest.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.cococo.netologytest.kot.Post

class PostRepositoryInFailImplementation(
    private val context: Context
) : PostRepository {

    private val gson = Gson()
    private var nextId = 1L
    private var posts = emptyList<Post>()
    private val postsFileName = "posts.json"
    private val nextIdFileName = "next_Id.json"
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type


    private val data = MutableLiveData(posts)

    init {
        val postsFail = context.filesDir.resolve(postsFileName)
        val nextIdFail = context.filesDir.resolve(nextIdFileName)

        posts = if (postsFail.exists()) {
            postsFail.reader().buffered().use {
                gson.fromJson(it, type)
            }
        } else {
            emptyList()
        }

        nextId = if (nextIdFail.exists()) {
            nextIdFail.reader().buffered().use {
                gson.fromJson(it, Long::class.java)
            }
        } else {
            nextId
        }

        data.value = posts
    }

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(
                likedByMe = !it.likedByMe,
                likes = it.likes + if (it.likedByMe) -1 else +1
            )
        }
        data.value = posts
        sync()
    }

    override fun shared(id: Long) {
        posts = posts.map {
            if (it.id != id || it.isSharedByMe) it else it.copy(
                isSharedByMe = true,
                shared = it.shared + 1
            )
        }
        data.value = posts
        sync()
    }

    override fun viewed(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(viewed = it.viewed + 1)
        }
        data.value = posts
        sync()
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
        sync()
    }

    override fun save(post: Post) {
        posts = if (post.id == 0L) {
            listOf(post.copy(id = nextId++, author = "Me", published = "Now")) + posts
        } else {
            posts.map {
                if (it.id != post.id) it else it.copy(content = post.content)
            }
        }
        data.value = posts
        sync()
    }

    private fun sync() {
        context.filesDir.resolve(postsFileName).writer().buffered().use {
            it.write(gson.toJson(posts))
        }
        context.filesDir.resolve(nextIdFileName).writer().buffered().use {
            it.write(gson.toJson(nextId))
        }

    }
}
