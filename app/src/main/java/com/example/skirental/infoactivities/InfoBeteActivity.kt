package com.example.skirental.infoactivities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.activities.CascaActivity
import com.example.skirental.databinding.ActivityInfoBeteBinding
import com.example.skirental.models.Produs

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
            var intent1 = Intent(this, CascaActivity::class.java)
            intent1.putExtra("inaltime",intent.getStringExtra("inaltime"))
            intent1.putExtra("marimepicior",intent.getStringExtra("marimepicior"))
            intent1.putExtra("sex",intent.getStringExtra("sex"))
            intent1.putExtra("varsta",intent.getStringExtra("varsta"))
            intent1.putExtra("schiuri",intent.getSerializableExtra("schiuri") as? Produs)
            intent1.putExtra("clapari",intent.getSerializableExtra("clapari") as? Produs)
            intent1.putExtra("bete",intent.getSerializableExtra("bete") as? Produs)
            startActivity(intent1)
        }
    }
}