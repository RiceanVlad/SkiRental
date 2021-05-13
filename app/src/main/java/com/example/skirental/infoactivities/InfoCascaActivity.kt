package com.example.skirental.infoactivities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.activities.OchelariActivity
import com.example.skirental.databinding.ActivityInfoCascaBinding
import com.example.skirental.models.Produs
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_info_casca.*

/**
 * Show's information about the chosen helmet
 */
class InfoCascaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoCascaBinding
    private val db = FirebaseFirestore.getInstance()
    private val TAG = "vlad"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoCascaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textViewBrandCascaS.setText(intent.getStringExtra("firma"))
        textViewCascaDescriere.setText(intent.getStringExtra("descriere"))

        butoane()
    }

    /**
     * Buttons on this activity
     * @param buttonAlegeCasca set helmet rent=true in database
     */
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

            val produs : Produs = intent.getSerializableExtra("casca") as Produs

            //update inchiriat = true
            db.collection("casti").document(produs.imagine)
                .update("inchiriat",true)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }

            startActivity(intent1)
        }
    }
}