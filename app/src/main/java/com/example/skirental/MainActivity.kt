package com.example.skirental

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.activities.CreateAccountActivity
import com.example.skirental.activities.FirstActivity
import com.example.skirental.administrator.AdministratorActivity
import com.example.skirental.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

/**
 *  This is the main class/ the login screen
 */

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

    /**
     *  Here are all the buttons on this activity
     *  @param buttonLogin Login button
     *  @param textViewNewUser Make an account
     */
    private fun butoaneMain(){
        binding.buttonLogin.setOnClickListener{

            if(binding.editTextPassword.text.toString().equals("admin123")){ // daca e administrator
                db.collection("users")
                        .whereEqualTo("email",binding.editTextEmail.text.toString())
                        .whereEqualTo("parola", binding.editTextPassword.text.toString())
                        .get()
                        .addOnSuccessListener { documents ->
                            if(documents.size() != 0){
                                    val intent = Intent(this, AdministratorActivity::class.java)
                                    startActivity(intent);
                            } else{
                                Toast.makeText(this, "Your login credentials don't match an account in our system.", Toast.LENGTH_SHORT).show()
                            }
                        }
                        .addOnFailureListener{ exception ->
                            Log.w(TAG,"Error getting documents: ", exception)
                        }

            }else{ //daca e utilizator
                db.collection("users")
                        .whereEqualTo("email",binding.editTextEmail.text.toString())
                        .whereEqualTo("parola", binding.editTextPassword.text.toString())
                        .get()
                        .addOnSuccessListener { documents ->
                            if(documents.size() != 0){
                                for(document in documents){
                                    val intent = Intent(this, FirstActivity::class.java)
                                    intent.putExtra("inaltime",document.get("inaltime").toString())
                                    intent.putExtra("marimepicior",document.get("marimepicior").toString())
                                    intent.putExtra("sex",document.get("sex").toString())
                                    intent.putExtra("varsta",document.get("varsta").toString())
                                    intent.putExtra("email",editTextEmail.text.toString())
                                    intent.putExtra("parola",editTextPassword.text.toString())
                                    startActivity(intent);
                                }
                            } else{
                                Toast.makeText(this, "Your login credentials don't match an account in our system.", Toast.LENGTH_SHORT).show()
                            }
                        }
                        .addOnFailureListener{ exception ->
                            Log.w(TAG,"Error getting documents: ", exception)
                        }
            }
            
        }
        binding.textViewNewUser.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent);
        }

    }


}