package ru.cococo.netologytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.cococo.netologytest.databinding.ActivityMainBinding
import ru.cococo.netologytest.kot.Post
import java.math.RoundingMode


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false
        )

        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            if (post.likedByMe) {
                like.setImageResource(R.drawable.baseline_favorite_24)
            }
            likeCount.text = getCountFormat(post.likes)
            shareCount.text = getCountFormat(post.shared)
            viewCount.text = getCountFormat(post.viewed)

            like.setOnClickListener {
                post.likedByMe = !post.likedByMe
                like.setImageResource(
                    if (post.likedByMe) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
                )
                if (post.likedByMe) post.likes++ else post.likes--
                likeCount.text = getCountFormat(post.likes)
            }

            share.setOnClickListener {
                shareCount.text = getCountFormat(post.shared++)
            }
            view.setOnClickListener {
                viewCount.text = getCountFormat(post.viewed++)
            }
        }
    }

    private fun getCountFormat(count: Int): String {
        return when (count) {
            in 0..999 -> count.toString()
            in 1000..9999 -> "${changeFormat(count / 1000.0)} K"
            in 10000..999999 -> "${count / 1000} K"
            in 1000000..9999999 -> "${changeFormat(count / 1000000.0)} М"
            else -> "${(count / 1000000)} M"
        }
    }

    private fun changeFormat(count: Double): String {
        val newCount = count.toBigDecimal().setScale(1, RoundingMode.DOWN).toDouble()
        return if (newCount % 1 == 0.0) {
            "${newCount.toInt()}"
        } else {
            newCount.toString()
        }

    }
}

