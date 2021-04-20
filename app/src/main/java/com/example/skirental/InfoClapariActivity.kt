package com.example.skirental

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.databinding.ActivityInfoClapariBinding

class InfoClapariActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoClapariBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoClapariBinding.inflate(layoutInflater)
        setContentView(binding.root)

        butoane()
    }

    private fun butoane(){
        binding.buttonAlegeClapari.setOnClickListener(){
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,BeteActivity::class.java)
            startActivity(intent)
        }
    }
}