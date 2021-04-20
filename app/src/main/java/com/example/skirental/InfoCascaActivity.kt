package com.example.skirental

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
        }
    }
}