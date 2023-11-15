package ru.cococo.netologytest.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels

import androidx.navigation.fragment.findNavController
import ru.cococo.netologytest.activity.NewPostFragment.Companion.textArg

import ru.cococo.netologytest.databinding.FragmentEditPostBinding
import ru.cococo.netologytest.databinding.FragmentNewPostBinding
import ru.cococo.netologytest.util.AndroidUtils
import ru.cococo.netologytest.util.StringArg
import ru.cococo.netologytest.viewmodel.PostViewModel


class EditPostFragment: Fragment()  {

    companion object {
        var Bundle.textArg: String? by StringArg
    }

    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEditPostBinding.inflate(
            inflater,
            container,
            false
        )


        arguments?.textArg
            ?.let(binding.editPost::setText)

        binding.saveEdit.setOnClickListener {
            viewModel.changeContent(binding.editPost.text.toString())
            viewModel.save(binding.editPost.text.toString())
            AndroidUtils.hideKeyboard(requireView())
            findNavController().navigateUp()
        }

        binding.cancelEdit.setOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root
    }

}
