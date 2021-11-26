package ru.fefu.activitytracker.outerTracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.fefu.activitytracker.databinding.RegistrationScreenBinding

class RegistrationScreenActivity : AppCompatActivity() {
    private lateinit var binding: RegistrationScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = RegistrationScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goBackButton.setOnClickListener {
            finish()
        }
    }
}