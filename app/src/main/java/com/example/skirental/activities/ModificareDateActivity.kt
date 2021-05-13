package com.example.skirental.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.MainActivity
import com.example.skirental.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_modificare_date.*

/**
 * User modifies personal data (in the Firestore database)
 */
class ModificareDateActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val TAG = "vlad"
    private var userId = "null"
    private var dificultate = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificare_date)

        butoane()
        preluareDateDinBD()

    }

    /**
     * Extract user data from database
     */
    private fun preluareDateDinBD() {
        db.collection("users")
            .whereEqualTo("email",intent.getStringExtra("email"))
            .whereEqualTo("parola",intent.getStringExtra("parola"))
            .get()
            .addOnSuccessListener { documents ->
                if(documents.size() != 0){
                    for(document in documents){
                        userId = document.id
                        editTextEmailM.setText(document.get("email").toString())
                        editTextNumeM.setText(document.get("nume").toString())
                        editTextGreutateM.setText(document.get("greutate").toString())
                        editTextMarimePiciorM.setText(document.get("marimepicior").toString())
                        seekBarModificare.setProgress(document.get("nivel").toString().toInt())
                        editTextVarstaM.setText(document.get("varsta").toString())
                        editTextInaltimeM.setText(document.get("inaltime").toString())
                        var sex = true
                        val str = document.get("sex").toString()
                        if(str.equals("false"))
                            sex = false
                        if(sex)
                            chipBaiat.checkedIcon
                        else
                            chipFata.checkedIcon
                    }
                } else{
                    Toast.makeText(this, "A problem has occured, please re-log.", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener{ exception ->
                Log.w(TAG,"Error getting documents: ", exception)
            }
    }

    /**
     * Buttons on this activity
     * @param buttonModificaDate Update data to database
     */
    private fun butoane() {
        buttonModificaDate.setOnClickListener {

            updateInBD()

            Toast.makeText(this, "Account successfully updated!", Toast.LENGTH_LONG).show()
            val intent1 = Intent(this,MainActivity::class.java)
            startActivity(intent1)
        }
        seekBarModificare?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                dificultate = progress
                if (progress <= 1)
                    Toast.makeText(this@ModificareDateActivity, "Beginner", Toast.LENGTH_SHORT).show()
                if (progress == 2 || progress == 3)
                    Toast.makeText(this@ModificareDateActivity, "Intermediate", Toast.LENGTH_SHORT).show()
                if (progress > 3 && progress <5)
                    Toast.makeText(this@ModificareDateActivity, "Advanced", Toast.LENGTH_SHORT).show()
                if (progress == 5)
                    Toast.makeText(this@ModificareDateActivity, "Pro", Toast.LENGTH_SHORT).show()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//                Toast.makeText(applicationContext,"start tracking",Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//                Toast.makeText(applicationContext,"stop tracking",Toast.LENGTH_SHORT).show()
            }
        })
    }

    /**
     * Update user data in database
     */
    private fun updateInBD() {

        var sex = true
        if(chipFata.isChecked)
            sex = false

        db.collection("users").document(userId)
            .update("email",editTextEmailM.text.toString(),
                "greutate",editTextGreutateM.text.toString().toInt(),
                "inaltime",editTextInaltimeM.text.toString().toInt(),
            "marimepicior",editTextMarimePiciorM.text.toString().toInt(),
            "nivel",dificultate,
            "nume",editTextNumeM.text.toString(),
            "sex",sex,
            "varsta",editTextVarstaM.text.toString().toInt())
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }
}