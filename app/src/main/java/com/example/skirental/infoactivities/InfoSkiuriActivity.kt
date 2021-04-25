package com.example.skirental.infoactivities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.activities.ClapariActivity
import com.example.skirental.databinding.ActivityInfoSkiuriBinding

class InfoSkiuriActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoSkiuriBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoSkiuriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewSkiAlese.setText(intent.getStringExtra("titlu"))
        binding.textViewUsername.setText(intent.getStringExtra("username"))

        butoane()
    }

    private fun butoane(){
        binding.buttonAlegeSkiuri.setOnClickListener(){
            val intent = Intent(this, ClapariActivity::class.java)
            startActivity(intent)
        }
        binding.buttonSkipSchiuri.setOnClickListener(){
            val intent = Intent(this, ClapariActivity::class.java)
            startActivity(intent)
        }
    }
}