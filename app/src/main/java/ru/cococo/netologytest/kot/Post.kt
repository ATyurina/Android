package ru.cococo.netologytest.kot

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likes: Int = 1999,
    val likedByMe: Boolean,
    val share: Int = 99,
    val view: Int = 1999999,
    val video: Uri? = null
)
