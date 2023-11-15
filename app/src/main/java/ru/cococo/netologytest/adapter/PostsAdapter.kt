package ru.cococo.netologytest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.cococo.netologytest.R
import ru.cococo.netologytest.databinding.FragmentCartPostBinding
import ru.cococo.netologytest.kot.GetCountFormat
import ru.cococo.netologytest.kot.Post

interface OnInteractionListener {

    fun open(post: Post)
    fun like(post: Post)
    fun share(post: Post)
    fun view(post: Post)
    fun remove(post: Post)
    fun edit(post: Post)

    fun playVideo(post: Post)
}

class PostsAdapter(
    private val onInteractionListener: OnInteractionListener
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding =
            FragmentCartPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

}

class PostViewHolder(
    private val binding: FragmentCartPostBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            like.isChecked = post.likedByMe
            group.visibility = if (post.video != null) {
                View.VISIBLE
            } else {
                View.GONE
            }

            val transform = GetCountFormat()
            like.text = transform.getFormat(post.likes)
            share.text = transform.getFormat(post.share)
            viewCount.text = transform.getFormat(post.view)
            content.setOnClickListener {
                onInteractionListener.open(post)
            }
            like.setOnClickListener {
                onInteractionListener.like(post)
            }
            share.setOnClickListener {
                onInteractionListener.share(post)
            }
            view.setOnClickListener {
                onInteractionListener.view(post)
            }
            play.setOnClickListener {
                onInteractionListener.playVideo(post)
            }
            video.setOnClickListener {
                onInteractionListener.playVideo(post)
            }
            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.menu_options)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.remove(post)
                                true
                            }

                            R.id.edit -> {
                                onInteractionListener.edit(post)
                                true
                            }

                            else -> false
                        }
                    }
                }.show()
            }
        }
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}
