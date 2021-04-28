package com.example.skirental.infoactivities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.activities.CascaActivity
import com.example.skirental.databinding.ActivityInfoBeteBinding
import com.example.skirental.models.Produs
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_info_bete.*

class InfoBeteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoBeteBinding
    private val db = FirebaseFirestore.getInstance()
    private val TAG = "vlad"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBeteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textViewBrandBeteS.setText(intent.getStringExtra("firma"))
        textViewDescriereBete.setText(intent.getStringExtra("descriere"))

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

            val produs : Produs = intent.getSerializableExtra("bete") as Produs

            //update inchiriat = true
            db.collection("bete").document(produs.imagine)
                .update("inchiriat",true)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }

            startActivity(intent1)
        }
    }
}