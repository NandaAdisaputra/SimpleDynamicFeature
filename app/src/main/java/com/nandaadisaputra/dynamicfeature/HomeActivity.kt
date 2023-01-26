package com.nandaadisaputra.dynamicfeature

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.nandaadisaputra.dynamicfeature.core.SessionManager
import com.nandaadisaputra.dynamicfeature.core.UserRepository
import com.nandaadisaputra.dynamicfeature.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val session = SessionManager(this)
        userRepository = UserRepository.getInstance(session)

        binding.tvWelcome.text = "Welcome ${userRepository.getUser()}"

        binding.btnLogout.setOnClickListener {
            userRepository.logoutUser()
            moveToMainActivity()
        }
        binding.fab.setOnClickListener {
            try {
//                moveToChatActivity()
                installChatModule()
            } catch (e: Exception){
                Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun installChatModule() {
        //TODO Kode dibawah ini digunakan untuk menginisialisasi SplitInstallManager.
        val splitInstallManager = SplitInstallManagerFactory.create(this)
        val moduleChat = "chat"
        if (splitInstallManager.installedModules.contains(moduleChat)) {
            moveToChatActivity()
            Toast.makeText(this, "Open module", Toast.LENGTH_SHORT).show()
        } else {
            //TODO memeriksa apakah suatu module sudah terinstall atau belum.
            // Jika sudah maka buka module dan jika belum lakukan proses instalasi.
            val request = SplitInstallRequest.newBuilder()
                .addModule(moduleChat)
                .build()
            splitInstallManager.startInstall(request)
                .addOnSuccessListener {
                    Toast.makeText(this, "Success installing module", Toast.LENGTH_SHORT).show()
                    moveToChatActivity()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error installing module", Toast.LENGTH_SHORT).show()
                }
        }
        //TODO Anda cukup menentukan module mana yang diinstal pada addModule.
    // Kemudian untuk memulai instalasi menggunakan fungsi startInstall.
    // Jika berhasil maka akan masuk ke addOnSuceessListener dan jika gagal
    // masuk ke addOnFailureListener.
    }

    private fun moveToChatActivity() {
        startActivity(Intent(this, Class.forName("com.nandaadisaputra.dynamicfeature.chat.ChatActivity")))
    }

    private fun moveToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}