package com.example.skirental.infoactivities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.activities.OchelariActivity
import com.example.skirental.databinding.ActivityInfoCascaBinding
import com.example.skirental.models.Produs

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
            var intent1 = Intent(this, OchelariActivity::class.java)
            intent1.putExtra("inaltime",intent.getStringExtra("inaltime"))
            intent1.putExtra("marimepicior",intent.getStringExtra("marimepicior"))
            intent1.putExtra("sex",intent.getStringExtra("sex"))
            intent1.putExtra("varsta",intent.getStringExtra("varsta"))
            intent1.putExtra("schiuri",intent.getSerializableExtra("schiuri") as? Produs)
            intent1.putExtra("clapari",intent.getSerializableExtra("clapari") as? Produs)
            intent1.putExtra("bete",intent.getSerializableExtra("bete") as? Produs)
            intent1.putExtra("casca",intent.getSerializableExtra("casca") as? Produs)
            startActivity(intent1)
        }
    }
}