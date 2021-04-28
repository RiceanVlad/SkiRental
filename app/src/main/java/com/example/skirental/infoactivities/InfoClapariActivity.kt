package com.example.skirental.infoactivities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.activities.BeteActivity
import com.example.skirental.databinding.ActivityInfoClapariBinding
import com.example.skirental.models.Produs
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_info_clapari.*

class InfoClapariActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoClapariBinding
    private val db = FirebaseFirestore.getInstance()
    private val TAG = "vlad"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoClapariBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textViewBrandClapariS.setText(intent.getStringExtra("firma"))
        textViewClapariDescriere.setText(intent.getStringExtra("descriere"))

        butoane()
    }

    private fun butoane(){
        binding.buttonAlegeClapari.setOnClickListener(){
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
            val intent1 = Intent(this, BeteActivity::class.java)
            intent1.putExtra("inaltime",intent.getStringExtra("inaltime"))
            intent1.putExtra("marimepicior",intent.getStringExtra("marimepicior"))
            intent1.putExtra("sex",intent.getStringExtra("sex"))
            intent1.putExtra("varsta",intent.getStringExtra("varsta"))
            intent1.putExtra("schiuri",intent.getSerializableExtra("schiuri") as? Produs)
            intent1.putExtra("clapari",intent.getSerializableExtra("clapari") as? Produs)

            val produs : Produs = intent.getSerializableExtra("clapari") as Produs

            //update inchiriat = true
            db.collection("clapari").document(produs.imagine)
                .update("inchiriat",true)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }

            startActivity(intent1)
        }
    }
}