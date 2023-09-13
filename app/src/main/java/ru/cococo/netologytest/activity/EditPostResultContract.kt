package ru.cococo.netologytest.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import ru.cococo.netologytest.kot.Post

class EditPostResultContract : ActivityResultContract<String, String?>()  {

    override fun createIntent(context: Context, input: String): Intent =
        Intent(context, EditPostActivity::class.java).putExtra("content", input)


    override fun parseResult(resultCode: Int, intent: Intent?): String? =
    if (resultCode == Activity.RESULT_OK) {
        intent?.getStringExtra(Intent.EXTRA_TEXT)
    } else {
        null
    }
}
