package com.example.skirental.infoactivities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.activities.ClapariActivity
import com.example.skirental.databinding.ActivityInfoSkiuriBinding
import com.example.skirental.models.Produs
import java.io.Serializable

class InfoSkiuriActivity : AppCompatActivity(), Serializable {

    private lateinit var binding: ActivityInfoSkiuriBinding
    private val TAG = "vlad"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoSkiuriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewSkiAlese.setText(intent.getStringExtra("descriere"))
        binding.textViewBrandSkiS.setText(intent.getStringExtra("firma"))

        butoane()
    }

    private fun butoane(){
        binding.buttonAlegeSkiuri.setOnClickListener(){
            val intent1 = Intent(this, ClapariActivity::class.java)
            intent1.putExtra("inaltime",intent.getStringExtra("inaltime"))
            intent1.putExtra("marimepicior",intent.getStringExtra("marimepicior"))
            intent1.putExtra("sex",intent.getStringExtra("sex"))
            intent1.putExtra("varsta",intent.getStringExtra("varsta"))
            intent1.putExtra("schiuri",intent.getSerializableExtra("schiuri") as? Produs)
            startActivity(intent1)
        }
    }
}