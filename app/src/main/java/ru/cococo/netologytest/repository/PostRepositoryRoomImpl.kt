package ru.cococo.netologytest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ru.cococo.netologytest.dao.PostDao
import ru.cococo.netologytest.entity.PostEntity
import ru.cococo.netologytest.kot.Post

class PostRepositoryRoomImpl(
    private val dao: PostDao
) : PostRepository {

    override fun getAll(): LiveData<List<Post>> = dao.getAll().map { list ->
        list.map { it.toDao() }
    }

    override fun save(post: Post) = dao.save(PostEntity.fromDto(post))

    override fun likeById(id: Long) = dao.likeById(id)

    override fun share(id: Long) = dao.share(id)

    override fun viewed(id: Long) = dao.viewed(id)

    override fun removeById(id: Long) = dao.removeById(id)

}
