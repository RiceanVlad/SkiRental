package com.example.skirental

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.databinding.ActivityInfoBeteBinding

class InfoBeteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoBeteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBeteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        butoane()
    }

    private fun butoane(){
        binding.buttonAlegeBete.setOnClickListener(){
            var intent = Intent(this,CascaActivity::class.java)
            startActivity(intent)
        }
    }
}