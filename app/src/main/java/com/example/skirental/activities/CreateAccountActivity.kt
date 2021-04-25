package com.example.skirental.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.databinding.ActivityCreateAccountBinding
import com.google.firebase.firestore.FirebaseFirestore

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAccountBinding
    val db = FirebaseFirestore.getInstance()
    private val TAG = "vlad"
    private var sem = false;
    private val utilizatori = db.collection("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        butoane()
    }

    private fun butoane(){
        binding.buttonCreate.setOnClickListener(){

            utilizatori
                    .whereEqualTo("email",binding.editTextCreazaEmail.text.toString())
                    .get()
                    .addOnSuccessListener { documents ->
                        if(documents.size() > 0){
                            // afiseaza ca exista email
                            Toast.makeText(this, "Email already exists.", Toast.LENGTH_SHORT).show()
                        }else{
                            //se adauga cont in baza de date
                            adaugaContInBazaDeDate()
                        }
                    }
                    .addOnFailureListener{ exception ->
                        Log.w(TAG,"Error getting documents: ", exception)
                    }
        }
    }

    private fun adaugaContInBazaDeDate(){
        var sex : Boolean = false
        if(binding.chipM.isChecked)
            sex = true
        val data = hashMapOf(
                "nume" to binding.editTextNume.text.toString(),
                "email" to binding.editTextCreazaEmail.text.toString(),
                "parola" to binding.editTextParola.text.toString(),
                "greutate" to binding.editTextGreutate.text.toString().toInt(),
                "inaltime" to binding.editTextInaltime.text.toString().toInt(),
                "marimepicior" to binding.editTextMarimePicior.text.toString().toInt(),
                "nivel" to binding.editTextDificultate.text.toString().toInt(),
                "sex" to sex,
                "varsta" to binding.editTextVarsta.text.toString().toInt()
        )
        Toast.makeText(this, "Account succesfully created.", Toast.LENGTH_SHORT).show()
        utilizatori.document().set(data)
    }

}