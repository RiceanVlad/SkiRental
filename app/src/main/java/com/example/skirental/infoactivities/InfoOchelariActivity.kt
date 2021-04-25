package com.example.skirental.infoactivities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.activities.ProdusActivity
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
            var intent = Intent(this, ProdusActivity::class.java)
            startActivity(intent)
        }
    }
}