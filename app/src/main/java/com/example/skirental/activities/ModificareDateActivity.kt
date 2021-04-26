package com.example.skirental.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.MainActivity
import com.example.skirental.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_modificare_date.*

class ModificareDateActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val TAG = "vlad"
    private var userId = "null"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificare_date)

        preluareDateDinBD()
        butoane()
    }

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
                        editTextNivelM.setText(document.get("nivel").toString())
                        editTextVarstaM.setText(document.get("varsta").toString())
                        editTextParolaM.setText(document.get("parola").toString())
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

    private fun butoane() {
        buttonModificaDate.setOnClickListener {

            updateInBD()

            Toast.makeText(this, "Account successfully updated!", Toast.LENGTH_LONG).show()
            val intent1 = Intent(this,MainActivity::class.java)
            startActivity(intent1)
        }
    }

    private fun updateInBD() {

        var sex = true
        if(chipFata.isChecked)
            sex = false

        db.collection("users").document(userId)
            .update("email",editTextEmailM.text.toString(),
                "greutate",editTextGreutateM.text.toString().toInt(),
                "inaltime",editTextInaltimeM.text.toString().toInt(),
            "marimepicior",editTextMarimePiciorM.text.toString().toInt(),
            "nivel",editTextNivelM.text.toString().toInt(),
            "nume",editTextNumeM.text.toString(),
            "parola",editTextParolaM.text.toString(),
            "sex",sex,
            "varsta",editTextVarstaM.text.toString().toInt())
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }
}