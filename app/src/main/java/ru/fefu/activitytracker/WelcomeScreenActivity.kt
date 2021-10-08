package ru.fefu.activitytracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.fefu.activitytracker.databinding.WelcomeScreenBinding

class WelcomeScreenActivity : AppCompatActivity() {
    private lateinit var binding: WelcomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = WelcomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginButton()
        registrationButton()
    }

    private fun loginButton() {
        binding.loginButton.setOnClickListener {
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registrationButton() {
        binding.registerButton.setOnClickListener {
            val intent = Intent(this, RegistrationScreenActivity::class.java)
            startActivity(intent)
        }
    }
}