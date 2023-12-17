package ru.cococo.netologytest.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import ru.cococo.netologytest.R

import ru.cococo.netologytest.activity.NewPostFragment.Companion.textArg
import ru.cococo.netologytest.adapter.OnInteractionListener
import ru.cococo.netologytest.adapter.PostsAdapter
import ru.cococo.netologytest.databinding.FragmentCartPostBinding
import ru.cococo.netologytest.databinding.FragmentNewPostBinding
import ru.cococo.netologytest.kot.GetCountFormat
import ru.cococo.netologytest.kot.Post
import ru.cococo.netologytest.util.ParcelableArg
import ru.cococo.netologytest.util.StringArg
import ru.cococo.netologytest.viewmodel.PostViewModel


class CardPostFragment : Fragment(R.layout.fragment_cart_post) {

//    companion object {
//        var Bundle.postArg: Parcelable? by ParcelableArg
//
//    }
//
//    private val viewModel: PostViewModel by viewModels(
//        ownerProducer = ::requireParentFragment
//    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val binding = FragmentCartPostBinding.inflate(
            inflater,
            container,
            false
        )


//       val postCard : Post = arguments?.postArg as Post


        return binding.root
    }
}
