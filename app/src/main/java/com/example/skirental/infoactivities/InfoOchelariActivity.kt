package com.example.skirental.infoactivities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.activities.ProdusActivity
import com.example.skirental.databinding.ActivityInfoOchelariBinding
import com.example.skirental.models.Produs
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_info_ochelari.*

class InfoOchelariActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoOchelariBinding
    private val db = FirebaseFirestore.getInstance()
    private val TAG = "vlad"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoOchelariBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textViewOchelariBrandS.setText(intent.getStringExtra("firma"))
        textViewOchelariDescriere.setText(intent.getStringExtra("descriere"))

        butoane()
    }

    private fun butoane(){
        binding.buttonAlegeOchelari.setOnClickListener(){
            var intent1 = Intent(this, ProdusActivity::class.java)
            intent1.putExtra("inaltime",intent.getStringExtra("inaltime"))
            intent1.putExtra("marimepicior",intent.getStringExtra("marimepicior"))
            intent1.putExtra("sex",intent.getStringExtra("sex"))
            intent1.putExtra("varsta",intent.getStringExtra("varsta"))
            intent1.putExtra("schiuri",intent.getSerializableExtra("schiuri") as? Produs)
            intent1.putExtra("clapari",intent.getSerializableExtra("clapari") as? Produs)
            intent1.putExtra("bete",intent.getSerializableExtra("bete") as? Produs)
            intent1.putExtra("casca",intent.getSerializableExtra("casca") as? Produs)
            intent1.putExtra("ochelari",intent.getSerializableExtra("ochelari") as? Produs)

            val produs : Produs = intent.getSerializableExtra("ochelari") as Produs

            //update inchiriat = true
            db.collection("ochelari").document(produs.imagine)
                .update("inchiriat",true)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }

            startActivity(intent1)
        }
    }
}