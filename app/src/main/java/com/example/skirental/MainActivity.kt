package com.example.skirental

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.activities.CreateAccountActivity
import com.example.skirental.activities.FirstActivity
import com.example.skirental.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val db = FirebaseFirestore.getInstance()
    private val TAG = "vlad"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        butoaneMain()
    }

    private fun butoaneMain(){
        binding.buttonLogin.setOnClickListener{

            db.collection("users")
                    .whereEqualTo("email",binding.editTextEmail.text.toString())
                    .whereEqualTo("parola", binding.editTextPassword.text.toString())
                    .get()
                    .addOnSuccessListener { documents ->
                        if(documents.size() != 0){
                            for(document in documents){
                                val intent = Intent(this, FirstActivity::class.java)
                                Log.d(TAG,document.get("inaltime").toString())
                                intent.putExtra("inaltime",document.get("inaltime").toString())
                                intent.putExtra("marimepicior",document.get("marimepicior").toString())
                                intent.putExtra("sex",document.get("sex").toString())
                                intent.putExtra("varsta",document.get("varsta").toString())
                                startActivity(intent);
                            }
                        } else{
                            Toast.makeText(this, "Incorrect data.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .addOnFailureListener{ exception ->
                        Log.w(TAG,"Error getting documents: ", exception)
                    }

            
        }
        binding.buttonCreateAccount.setOnClickListener{
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent);
        }

    }


}