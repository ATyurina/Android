package ru.cococo.netologytest.activity


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.cococo.netologytest.R

import ru.cococo.netologytest.activity.NewPostFragment.Companion.textArg
import ru.cococo.netologytest.adapter.OnInteractionListener
import ru.cococo.netologytest.adapter.PostsAdapter
import ru.cococo.netologytest.databinding.FragmentFeedBinding
import ru.cococo.netologytest.kot.Post
import ru.cococo.netologytest.viewmodel.PostViewModel


class FeedFragment : Fragment() {

    val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeedBinding.inflate(
            inflater,
            container,
            false
        )

        val adapter = PostsAdapter(object : OnInteractionListener {
            override fun open(post: Post) {
                findNavController().navigate(R.id.action_feedFragment_to_cardPostFragment)
                viewModel.edit(post)
            }
//                    R.id.action_feedFragment_to_cardPostFragment,
//                    Bundle().apply {
//                        postArg = post
//                    })}

            override fun like(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun viewed(post: Post) {
                viewModel.viewed(post.id)
            }

            override fun remove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun edit(post: Post) {
                findNavController().navigate(R.id.action_feedFragment_to_editPostFragment,
                    Bundle().apply {
                        textArg = post.content
                    })
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
                viewModel.share(post.id)
            }


            override fun playVideo(post: Post) {
                val intent = Intent(Intent.ACTION_VIEW, post.video)
                val playVideoIntent =
                    Intent.createChooser(intent, getString(R.string.chooser_play_video))
                startActivity(playVideoIntent)
            }
        })

        binding.list.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { posts ->
            adapter.submitList(posts)
        }


        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_feedFragment_to_newPostFragment)
        }

        return binding.root
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


