package ru.cococo.netologytest.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import ru.cococo.netologytest.databinding.ActivityEditPostBinding
import ru.cococo.netologytest.databinding.ActivityNewPostBinding

class EditPostActivity: AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEditPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle: Bundle? = intent.extras
        val text: String? = bundle?.getString("content")
        binding.editPost.setText(text)
        binding.editPost.requestFocus()
        binding.cancelEdit.setOnClickListener {
            finish()
        }
        binding.saveEdit.setOnClickListener {
            val intent = Intent()
            if (binding.editPost.text.isNullOrBlank()){
                setResult(Activity.RESULT_CANCELED, intent)
            } else {
                val editText = binding.editPost.text.toString()
                intent.putExtra(Intent.EXTRA_TEXT, editText)
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }
}
