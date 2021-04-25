package com.example.skirental.infoactivities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.activities.OchelariActivity
import com.example.skirental.databinding.ActivityInfoCascaBinding

class InfoCascaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoCascaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoCascaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        butoane()
    }

    private fun butoane(){
        binding.buttonAlegeCasca.setOnClickListener(){
            var intent = Intent(this, OchelariActivity::class.java)
            startActivity(intent)
        }
    }
}