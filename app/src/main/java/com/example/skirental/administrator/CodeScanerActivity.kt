package com.example.skirental.administrator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.skirental.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_code_scaner.*

/**
 * Scan product to make it rent ready again
 */
class CodeScanerActivity : AppCompatActivity() {


    private val TAG = "vlad"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_scaner)

        butoane()

    }

    /**
     * Buttons on this activity
     * @param buttonScan initiate Barcode & QR scanner
     */
    private fun butoane() {
        buttonScan.setOnClickListener {
            val scanner = IntentIntegrator(this)

            scanner.initiateScan()
        }
    }

    /**
     * If something is scanned then update product
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null)
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
                } else {
//                    update inchiriat = false
                        val colectii = ArrayList<String>()
                    colectii.addAll(arrayListOf("schiuri","clapari","bete","casti","ochelari"))

                    for(colectie in colectii){
                        updateReturnat(colectie,result.contents)
                    }

                    Toast.makeText(this, "Product: ${result.contents} is now updated and rent ready.", Toast.LENGTH_LONG).show()
                }
            else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    /**
     * Update product to make it rent ready in database
     */
    private fun updateReturnat(colectie: String, cheie: String) {
        val db = FirebaseFirestore.getInstance()
        db.collection(colectie).document(cheie)
            .update("inchiriat",false)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }


}