package com.example.skirental

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.databinding.ActivityInfoSkiuriBinding

class InfoSkiuriActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoSkiuriBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoSkiuriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewSkiAlese.setText(intent.getStringExtra("titlu"))
        binding.textViewUsername.setText(intent.getStringExtra("username"))
    }
}