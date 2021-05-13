package com.example.skirental.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.databinding.ActivityCreateAccountBinding
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Create account in 2 steps
 */
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

    /**
     * Buttons in this activity
     * @param buttonNext next step for creating account
     */
    private fun butoane(){

        binding.buttonNext.setOnClickListener {
            val intent = Intent(this,CreateAccount2Activity::class.java)
            intent.putExtra("nume",binding.editTextNume.text.toString())
            intent.putExtra("email",binding.editTextCreazaEmail.text.toString())
            intent.putExtra("parola",binding.editTextParola.text.toString())
            intent.putExtra("reParola",binding.editTextReParola.text.toString())
            startActivity(intent)
        }
    }



}