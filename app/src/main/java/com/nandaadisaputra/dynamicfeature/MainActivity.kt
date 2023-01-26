package com.nandaadisaputra.dynamicfeature

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nandaadisaputra.dynamicfeature.core.SessionManager
import com.nandaadisaputra.dynamicfeature.core.UserRepository
import com.nandaadisaputra.dynamicfeature.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val session = SessionManager(this)
        userRepository = UserRepository.getInstance(session)

        if (userRepository.isUserLogin()) {
            moveToHomeActivity()
        }

        binding.btnLogin.setOnClickListener {
            saveSession()
        }
    }

    private fun saveSession() {
        userRepository.loginUser(binding.edUsername.text.toString())
        moveToHomeActivity()
    }

    private fun moveToHomeActivity() {
        //TODO Karena antara module tidak tersambung, Anda perlu menuliskan nama Activity secara manual seperti berikut.
        startActivity(Intent(this, Class.forName("com.nandaadisaputra.dynamicfeature.HomeActivity")))
//        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}