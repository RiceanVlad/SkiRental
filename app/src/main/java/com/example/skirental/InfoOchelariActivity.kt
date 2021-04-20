package com.example.skirental

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.databinding.ActivityInfoOchelariBinding

class InfoOchelariActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoOchelariBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoOchelariBinding.inflate(layoutInflater)
        setContentView(binding.root)

        butoane()
    }

    private fun butoane(){
        binding.buttonAlegeOchelari.setOnClickListener(){
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
        }
    }
}