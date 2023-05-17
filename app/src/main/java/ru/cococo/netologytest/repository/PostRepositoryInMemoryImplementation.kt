package ru.cococo.netologytest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.cococo.netologytest.kot.Post

class PostRepositoryInMemoryImplementation: PostRepository {

    private var post = Post(
        id = 1,
        author = "Нетология. Университет интернет-профессий будущего",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
        published = "21 мая в 18:36",
        likedByMe = false
    )

    private val data = MutableLiveData(post)

    override fun get() = data

    override fun like() {
        post = post.copy(likedByMe = !post.likedByMe, likes = post.likes + if(post.likedByMe) - 1 else + 1)
        data.value = post
    }

    override fun shared() {
        post = post.copy(shared = post.shared + 1)
        data.value = post
    }

    override fun viewed() {
        post = post.copy(viewed = post.viewed + 1)
        data.value = post
    }
}