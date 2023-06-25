package ru.cococo.netologytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.cococo.netologytest.adapter.OnLikeListener
import ru.cococo.netologytest.adapter.OnShareListener
import ru.cococo.netologytest.adapter.PostsAdapter
import ru.cococo.netologytest.databinding.ActivityMainBinding
import ru.cococo.netologytest.kot.Post
import ru.cococo.netologytest.viewmodel.PostViewModel


class MainActivity() : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        val adapter = PostsAdapter (
            {viewModel.likeById(it.id)},
            {viewModel.shared(it.id)},
            {viewModel.viewed(it.id)}
        )

        binding.list.adapter = adapter

        viewModel.data.observe(this) {posts->
            adapter.submitList(posts)
        }
    }
}

