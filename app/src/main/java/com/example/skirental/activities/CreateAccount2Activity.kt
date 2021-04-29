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
import kotlinx.android.synthetic.main.activity_create_account2.*

class CreateAccount2Activity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    private val TAG = "vlad"
    private val utilizatori = db.collection("users")
    private var dificultate = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account2)

        butoane()
    }

    private fun butoane() {
        buttonCreate.setOnClickListener {  

            utilizatori
                    .whereEqualTo("email",intent.getStringExtra("email"))
                    .get()
                    .addOnSuccessListener { documents ->
                        if(documents.size() > 0){
                            // afiseaza ca exista email
                            Toast.makeText(this, "Email already exists.", Toast.LENGTH_SHORT).show()
                        }else{
                            //se adauga cont in baza de date
                            adaugaContInBazaDeDate()
                            Toast.makeText(this, "Account successfully created.", Toast.LENGTH_LONG).show()
                            val intent1 = Intent(this,MainActivity::class.java)
                            startActivity(intent1)
                        }
                    }
                    .addOnFailureListener{ exception ->
                        Log.w(TAG,"Error getting documents: ", exception)
                    }
        }
        seekBarAlegeDif?.setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                dificultate = progress
                if (progress <= 1)
                    Toast.makeText(this@CreateAccount2Activity, "Beginner", Toast.LENGTH_SHORT).show()
                if (progress == 2 || progress == 3)
                    Toast.makeText(this@CreateAccount2Activity, "Intermediate", Toast.LENGTH_SHORT).show()
                if (progress > 3 && progress <5)
                    Toast.makeText(this@CreateAccount2Activity, "Advanced", Toast.LENGTH_SHORT).show()
                if (progress == 5)
                    Toast.makeText(this@CreateAccount2Activity, "Pro", Toast.LENGTH_SHORT).show()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //Toast.makeText(applicationContext,"start tracking",Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //Toast.makeText(applicationContext,"stop tracking",Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun adaugaContInBazaDeDate(){
        var sex : Boolean = false
        if(chipMale.isChecked)
            sex = true

        val data = hashMapOf(
                "nume" to intent.getStringExtra("nume"),
                "email" to intent.getStringExtra("email"),
                "parola" to intent.getStringExtra("parola"),
                "greutate" to editTextGreutate.text.toString().toInt(),
                "inaltime" to editTextInaltime.text.toString().toInt(),
                "marimepicior" to editTextMarimePicior.text.toString().toInt(),
                "nivel" to dificultate,
                "sex" to sex,
                "varsta" to editTextVarsta.text.toString().toInt()
        )
        Toast.makeText(this, "Account succesfully created.", Toast.LENGTH_SHORT).show()
        utilizatori.document().set(data)
    }
}