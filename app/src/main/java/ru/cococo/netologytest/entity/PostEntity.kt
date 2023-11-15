package ru.cococo.netologytest.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.cococo.netologytest.kot.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likes: Int,
    val likedByMe: Boolean,
    val share: Int,
    val viewed: Int
) {
    fun toDao() = Post(id, author, content, published, likes, likedByMe, share, viewed)

    companion object {
        fun fromDto(post: Post) =
            PostEntity(
                post.id,
                post.author,
                post.content,
                post.published,
                post.likes,
                post.likedByMe,
                post.share,
                post.viewed
            )
    }
}
