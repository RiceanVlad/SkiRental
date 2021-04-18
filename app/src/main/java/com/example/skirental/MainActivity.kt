package com.example.skirental

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        butoaneMain()
    }

    private fun butoaneMain(){
        binding.buttonLogin.setOnClickListener{
            val intent = Intent(this, FirstActivity::class.java)
            startActivity(intent);
        }
        binding.buttonCreateAccount.setOnClickListener{
            val intent = Intent(this, CreateAccount::class.java)
            startActivity(intent);
        }
    }
}