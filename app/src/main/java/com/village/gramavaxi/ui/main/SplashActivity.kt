package com.village.gramavaxi.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.village.gramavaxi.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val mobile = binding.etMobile.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (mobile.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this,
                    "ದಯವಿಟ್ಟು ಎಲ್ಲಾ ವಿವರಗಳನ್ನು ತುಂಬಿಸಿ / Please fill all details",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            // Simulated login — go to MainActivity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}