package com.example.skirental.infoactivities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.activities.ClapariActivity
import com.example.skirental.databinding.ActivityInfoSkiuriBinding
import com.example.skirental.models.Produs
import com.google.firebase.firestore.FirebaseFirestore
import java.io.Serializable

class InfoSkiuriActivity : AppCompatActivity(), Serializable {

    private lateinit var binding: ActivityInfoSkiuriBinding
    private val db = FirebaseFirestore.getInstance()
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

            val produs : Produs = intent.getSerializableExtra("schiuri") as Produs

            //update inchiriat = true
            db.collection("schiuri").document(produs.imagine)
                .update("inchiriat",true)
                    .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                    .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }

            startActivity(intent1)
        }
    }
}