package com.example.animatedonboarding

import android.graphics.Bitmap
import com.example.animatedonboarding.data.Chat


data class ChatState (
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    val bitmap: Bitmap? = null
)