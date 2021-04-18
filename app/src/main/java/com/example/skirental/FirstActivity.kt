package com.example.skirental

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var smth = 0;
        butoane()
    }

    private fun butoane(){
        binding.buttonRentSkiEquipment.setOnClickListener(){
            Toast.makeText(this, "am dat click", Toast.LENGTH_SHORT).show()
        }
    }
}