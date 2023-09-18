package ru.cococo.netologytest.activity


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.launch
import androidx.activity.viewModels
import ru.cococo.netologytest.R
import ru.cococo.netologytest.adapter.OnInteractionListener
import ru.cococo.netologytest.adapter.PostsAdapter
import ru.cococo.netologytest.databinding.ActivityMainBinding
import ru.cococo.netologytest.kot.Post
import ru.cococo.netologytest.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref = getPreferences(Context.MODE_PRIVATE)

        val result = pref.getString("key", null)
        println(result)


        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        val adapter = PostsAdapter(object : OnInteractionListener {
            override fun like(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun view(post: Post) {
                viewModel.viewed(post.id)
            }

            override fun remove(post: Post) {
                viewModel.removeByTd(post.id)
            }

            val editPostLauncher = registerForActivityResult(EditPostResultContract()) { result ->
                if (result.isNullOrBlank()) {
                    viewModel.clear()
                    return@registerForActivityResult
                }
                viewModel.changeContentAndSave(result)
            }

            override fun edit(post: Post) {
                editPostLauncher.launch(post.content)
                viewModel.edit(post)
            }

            override fun share(post: Post) {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }
                val shareIntent =
                    Intent.createChooser(intent, getString(R.string.chooser_share_post))
                startActivity(shareIntent)
            }


            override fun playVideo(post: Post) {
                val intent = Intent(Intent.ACTION_VIEW, post.video)
                val playVideoIntent =
                    Intent.createChooser(intent, getString(R.string.chooser_play_video))
                startActivity(playVideoIntent)
            }
        })

        binding.list.adapter = adapter

        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        val newPostLauncher = registerForActivityResult(NewPostResultContract()) { result ->
            result ?: return@registerForActivityResult
            viewModel.changeContentAndSave(result)
        }

        binding.fab.setOnClickListener {
            newPostLauncher.launch()
        }

    }
}


//        viewModel.edited.observe(this) {
//            if (it.id != 0L) {
//                binding.content.setText(it.content)
//                binding.content.focusAndShowKeyboard()
//                binding.group.visibility = View.VISIBLE
//                binding.message.text = it.content
//            }
//        }
//
//        binding.cancel.setOnClickListener {
//            viewModel.clear()
//            binding.content.setText("")
//            binding.content.clearFocus()
//            binding.group.visibility = View.GONE
//            AndroidUtils.hideKeyboard(it)
//        }
//        binding.save.setOnClickListener {
//            binding.group.visibility = View.GONE
//            val text = binding.content.text.toString()
//            if (text.isEmpty()) {
//                Toast.makeText(this, R.string.error_empty_content, Toast.LENGTH_LONG).show()
//                return@setOnClickListener
//            }
//            viewModel.changeContentAndSave(text)
//
//            binding.content.setText("")
//            binding.content.clearFocus()
//            AndroidUtils.hideKeyboard(it)
//        }


