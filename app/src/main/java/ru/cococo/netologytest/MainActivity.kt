package ru.cococo.netologytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import ru.cococo.netologytest.adapter.OnInteractionListener
import ru.cococo.netologytest.adapter.PostsAdapter
import ru.cococo.netologytest.databinding.ActivityMainBinding
import ru.cococo.netologytest.kot.Post
import ru.cococo.netologytest.util.AndroidUtils
import ru.cococo.netologytest.util.AndroidUtils.focusAndShowKeyboard
import ru.cococo.netologytest.viewmodel.PostViewModel


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        val adapter = PostsAdapter (object: OnInteractionListener{
            override fun like(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun share(post: Post) {
                viewModel.shared(post.id)
            }

            override fun view(post: Post) {
                viewModel.viewed(post.id)
            }

            override fun remove(post: Post) {
                viewModel.removeByTd(post.id)
            }

            override fun edit(post: Post) {
                viewModel.edit(post)
            }

        })

        binding.list.adapter = adapter

        viewModel.data.observe(this) {posts->
            val newPost = posts.size > adapter.currentList.size
            adapter.submitList(posts) {
                if (newPost) {
                    binding.list.smoothScrollToPosition(0)
                }
            }
        }

        viewModel.edited.observe(this) {
            if (it.id != 0L) {
                binding.content.setText(it.content)
                binding.content.focusAndShowKeyboard()
                binding.group.visibility = View.VISIBLE
                binding.message.text = it.content
            }
        }

        binding.cancel.setOnClickListener {
            binding.content.setText("")
            binding.content.clearFocus()
            binding.group.visibility = View.GONE
            AndroidUtils.hideKeyboard(it)
        }
        binding.save.setOnClickListener {
            binding.group.visibility = View.GONE
            val text = binding.content.text.toString()
            if (text.isEmpty()) {
                Toast.makeText(this, R.string.error_empty_content, Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            viewModel.changeContentAndSave(text)

            binding.content.setText("")
            binding.content.clearFocus()
            AndroidUtils.hideKeyboard(it)
        }
    }
}

