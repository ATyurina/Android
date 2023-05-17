package ru.cococo.netologytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.cococo.netologytest.databinding.ActivityMainBinding
import ru.cococo.netologytest.kot.GetCountFormat
import ru.cococo.netologytest.kot.Post
import ru.cococo.netologytest.viewmodel.PostViewModel
import java.math.RoundingMode


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        viewModel.data.observe(this){ post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                like.setImageResource(
                    if (post.likedByMe) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
                )
                val transform  = GetCountFormat()
                likeCount.text = transform.getFormat(post.likes)
                shareCount.text = transform.getFormat(post.shared)
                viewCount.text = transform.getFormat(post.viewed)

            }
            binding.like.setOnClickListener {
                viewModel.like()
            }
            binding.share.setOnClickListener {
                viewModel.shared()
            }
            binding.view.setOnClickListener {
                viewModel.viewed()
            }
        }

        println("OnCreate $this")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy $this")
    }
    override fun onStart() {
        super.onStart()
        println("onStart $this")
    }

    override fun onPause() {
        super.onPause()
        println("onPause $this")
    }

    override fun onStop() {
        super.onStop()
        println("onStop $this")
    }

    override fun onResume() {
        super.onResume()
        println("onResume $this")
    }
}

