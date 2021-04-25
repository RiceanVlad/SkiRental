package com.example.skirental.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        butoane()
    }

    private fun butoane(){
        binding.buttonRentSkiEquipment.setOnClickListener(){
            val intent1 = Intent(this, SkiuriActivity::class.java)
            intent1.putExtra("inaltime",intent.getStringExtra("inaltime"))
            intent1.putExtra("marimepicior",intent.getStringExtra("marimepicior"))
            intent1.putExtra("sex",intent.getStringExtra("sex"))
            intent1.putExtra("varsta",intent.getStringExtra("varsta"))
            startActivity(intent1);
        }
    }
}