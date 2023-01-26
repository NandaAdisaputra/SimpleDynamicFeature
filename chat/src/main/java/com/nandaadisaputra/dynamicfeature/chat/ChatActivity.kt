package com.nandaadisaputra.dynamicfeature.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.nandaadisaputra.dynamicfeature.core.SessionManager
import com.nandaadisaputra.dynamicfeature.core.UserRepository

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val tvChat = findViewById<TextView>(R.id.tv_chat)
        val session = SessionManager(this)
        val userRepository = UserRepository.getInstance(session)
        tvChat.text = "Hello ${userRepository.getUser()}! \n Welcome to Chat Feature"

    }
}